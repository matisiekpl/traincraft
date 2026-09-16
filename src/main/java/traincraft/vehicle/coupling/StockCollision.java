package traincraft.vehicle.coupling;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.MinecartFurnace;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import traincraft.Traincraft;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * CE's rolling-stock and additional collision handlers, sharing contact detection and pair
 * bookkeeping. Bogies are simulation points in this port, so their contacts are checked here.
 */
public final class StockCollision {
    private long lastTick = Long.MIN_VALUE;
    private final Set<Integer> handled = new HashSet<>();

    private static final ResourceKey<net.minecraft.world.damagesource.DamageType> RAN_OVER =
            ResourceKey.create(
                    Registries.DAMAGE_TYPE,
                    Identifier.fromNamespaceAndPath(Traincraft.MODID, "ran_over"));

    public StockCollision() {}

    private boolean hasHandled(long tick, int id) {
        if (lastTick != tick) {
            handled.clear();
            lastTick = tick;
        }
        return handled.contains(id);
    }

    public void tick(RollingStockEntity self) {
        if (self.level().isClientSide()) return;
        Set<Entity> nearby = new LinkedHashSet<>();
        double angle = Math.toRadians(self.getYRot());
        double extraX = 0.2 + Math.abs(Math.sin(angle)) * 2.0;
        double extraZ = 0.2 + Math.abs(Math.cos(angle)) * 2.0;
        for (Vec3 contact : contacts(self)) {
            AABB box = self.getBoundingBox().move(contact.subtract(self.position()));
            nearby.addAll(self.level().getEntities(self, box.inflate(extraX, 0.0, extraZ)));
        }
        for (Entity other : nearby) apply(self, other);
    }

    private static java.util.List<Vec3> contacts(Entity entity) {
        if (entity instanceof RollingStockEntity stock && stock.bogiePosition() != null) {
            return java.util.List.of(stock.position(), stock.bogiePosition());
        }
        return java.util.List.of(entity.position());
    }

    private static Vec3 separation(RollingStockEntity self, Entity other) {
        Vec3 nearest = other.position().subtract(self.position());
        for (Vec3 one : contacts(self)) {
            for (Vec3 two : contacts(other)) {
                Vec3 candidate = two.subtract(one);
                if (candidate.horizontalDistanceSqr() < nearest.horizontalDistanceSqr()) {
                    nearest = candidate;
                }
            }
        }
        return nearest;
    }

    public static void apply(RollingStockEntity self, Entity other) {
        if (self.level().isClientSide()
                || self == other
                || self.isRemoved()
                || other.isRemoved()
                || self.noPhysics
                || other.noPhysics
                || other.isSpectator()
                || self.isPassengerOfSameVehicle(other)) return;
        if (self.acceptsLivestock()
                && other instanceof Mob mob
                && self.getFirstPassenger() == null
                && !mob.isPassenger()) {
            mob.startRiding(self);
            return;
        }
        boolean cart = other instanceof RollingStockEntity || other instanceof AbstractMinecart;
        if (!cart && (!other.isPushable() || other.isPassenger())) return;
        if (other instanceof RollingStockEntity stock
                && (self.cartLinked1 == stock
                        || self.cartLinked2 == stock
                        || stock.cartLinked1 == self
                        || stock.cartLinked2 == self
                        || self.consist != null && self.consist == stock.consist)) return;

        Vec3 delta = separation(self, other);
        double distance = delta.horizontalDistance();
        double reach = cart ? self.getLinkageDistance(self) : 0.7;
        if (distance < 1.0E-4 || distance > reach) return;
        if (cart) {
            // Minecraft yaw is zero towards +Z, unlike CE's model-space yaw.
            double angle = Math.toRadians(self.getYRot());
            double alignment = (-Math.sin(angle) * delta.x + Math.cos(angle) * delta.z) / distance;
            if (Math.abs(alignment) < 0.8) return;
            // The angle test alone admits a staggered cart on the next parallel track.
            double lateral = Math.abs(Math.cos(angle) * delta.x + Math.sin(angle) * delta.z);
            if (lateral >= (self.getBbWidth() + other.getBbWidth()) * 0.5) return;
        }

        long tick = self.level().getGameTime();
        if (self.collisions().hasHandled(tick, other.getId())) return;
        if (other instanceof RollingStockEntity stock) {
            if (stock.collisions().hasHandled(tick, self.getId())) return;
            stock.collisions().handled.add(self.getId());
        }
        self.collisions().handled.add(other.getId());

        // Additional CollisionHandler's inverse-distance impulse, also used by short wagons.
        double strength = Math.min(1.0, 1.0 / distance) * 0.1 * 0.4 * 0.5;
        double dx = delta.x / distance * strength;
        double dz = delta.z / distance * strength;
        if (cart) {
            pushStock(self, other, dx, dz);
            if (other instanceof RollingStockEntity stock) {
                separate(self, stock, delta, distance, reach - BUFFER_GIVE);
            }
        } else {
            pushOther(self, other, delta, dx, dz);
        }
    }

    private static final double BUFFER_GIVE = 0.15;

    private static void separate(
            RollingStockEntity self, RollingStockEntity other, Vec3 delta, double distance, double minimum) {
        if (distance >= minimum) return;
        Vec3 axis = new Vec3(delta.x, 0.0, delta.z).scale(1.0 / distance);
        double overlap = minimum - distance;
        double selfApproach = self.getDeltaMovement().dot(axis);
        double otherApproach = -other.getDeltaMovement().dot(axis);
        double selfShare =
                selfApproach > 0 && otherApproach > 0 ? 0.5 : otherApproach > 0 ? 0.0 : 1.0;
        self.shiftAlongTrack(axis.scale(-overlap * selfShare));
        other.shiftAlongTrack(axis.scale(overlap * (1.0 - selfShare)));
        if (selfApproach > 0) {
            self.setDeltaMovement(self.getDeltaMovement().subtract(axis.scale(selfApproach)));
        }
        if (otherApproach > 0) {
            other.setDeltaMovement(other.getDeltaMovement().add(axis.scale(otherApproach)));
        }
    }

    private static void pushStock(RollingStockEntity self, Entity other, double d0, double d1) {
        double d9 = other.getDeltaMovement().x + self.getDeltaMovement().x;
        double d8 = other.getDeltaMovement().z + self.getDeltaMovement().z;
        boolean otherPowered =
                other instanceof RollingStockEntity stock
                        ? stock.isLocomotive()
                        : other instanceof MinecartFurnace;
        boolean selfPowered = self.isLocomotive();

        if (otherPowered == selfPowered) {
            // Neither drives the other: they share out what they were both carrying, and a
            // locomotive being run into reverses the sign of it.
            d9 *= selfPowered ? 0.4 : 0.5;
            d8 *= selfPowered ? 0.4 : 0.5;
            if (other instanceof LocomotiveEntity) {
                d9 *= -1.0;
                d8 *= -1.0;
            }
            float retention = selfPowered ? 0.2F : 0.02F;
            double separationScale = selfPowered ? 1.0 : 0.5;
            scale(self, retention);
            self.push(d9 - d0 * separationScale, 0.0, d8 - d1 * separationScale);
            scale(other, retention);
            other.push(d9 + d0 * separationScale, 0.0, d8 + d1 * separationScale);
        } else if (selfPowered) {
            scale(other, 0.2F);
            other.push(self.getDeltaMovement().x + d0, 0.0, self.getDeltaMovement().z + d1);
            scale(self, 0.95F);
        } else {
            scale(self, 0.2F);
            self.push(other.getDeltaMovement().x - d0, 0.0, other.getDeltaMovement().z - d1);
            scale(other, 0.95F);
        }
    }

    private static void pushOther(
            RollingStockEntity self, Entity other, Vec3 delta, double dx, double dz) {
        Vec3 motion = self.getDeltaMovement();
        double horizontal = motion.horizontalDistance();
        if (other instanceof Player && !self.isLocomotive()) {
            // A player can shove a freight wagon. Avoid CE's division by zero at rest.
            self.push(-dx, 0.0, -dz);
            if (horizontal > 1.0E-6) {
                other.push(-motion.x / horizontal * 0.006, 0.0, -motion.z / horizontal * 0.006);
            }
            return;
        }
        if (other instanceof LivingEntity && self.level() instanceof ServerLevel level) {
            double speed = motion.length() * 60.0;
            double kmh = speed * 3.6;
            if (kmh < 35.0) {
                if (horizontal > 1.0E-6) {
                    other.push(motion.x / horizontal * 0.06, 0.0, motion.z / horizontal * 0.06);
                } else {
                    double distance = delta.horizontalDistance();
                    double strength = Math.min(1.0, 1.0 / distance) * 0.05 * (2.0 / 3.0);
                    other.push(delta.x / distance * strength, 0.0, delta.z / distance * strength);
                }
                return;
            }
            // CE's extra handler has a higher threshold for players than for mobs.
            if (!(other instanceof Player) || kmh > 60.0) {
                int damage = (int) Math.ceil(speed * (other instanceof Creeper ? 100 : 1));
                other.hurtServer(level, level.damageSources().source(RAN_OVER, self), damage);
                if (horizontal > 1.0E-6) {
                    other.push(motion.x / horizontal * 1.2, 0.0, motion.z / horizontal * 1.2);
                }
            }
            return;
        }
        if (!(other instanceof ItemEntity)) self.push(-dx * 2.0, 0.0, -dz * 2.0);
        other.push(dx * 2.0, 0.0, dz * 2.0);
    }

    private static void scale(Entity entity, float factor) {
        entity.setDeltaMovement(
                entity.getDeltaMovement().x * factor,
                entity.getDeltaMovement().y,
                entity.getDeltaMovement().z * factor);
    }
}
