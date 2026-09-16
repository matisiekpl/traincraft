package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.vehicle.entity.FreightEntity;
import traincraft.vehicle.entity.TankCartEntity;
import traincraft.vehicle.entity.TenderEntity;
import traincraft.vehicle.entity.ZeppelinEntity;
import traincraft.vehicle.entity.TracksBuilderEntity;
import traincraft.vehicle.entity.WorkCartEntity;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.inventory.FreightMenu;
import traincraft.vehicle.inventory.TankMenu;
import traincraft.vehicle.inventory.TenderMenu;
import traincraft.vehicle.inventory.ZeppelinMenu;
import traincraft.vehicle.inventory.TracksBuilderMenu;
import traincraft.vehicle.inventory.WorkCartMenu;
import traincraft.vehicle.inventory.LocomotiveMenu;

/**
 * Menu type registry.
 *
 * <p>Built through {@code IMenuTypeExtension.create} rather than {@code MenuType::new} because the
 * client needs the entity id to find the locomotive the menu belongs to, and only the extension
 * form carries extra data across.
 */
public final class MenuRegistry {

    public static final DeferredRegister<MenuType<?>> TYPES =
            DeferredRegister.create(Registries.MENU, Traincraft.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<LocomotiveMenu>> LOCO =
            TYPES.register(
                    "loco",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof LocomotiveEntity loco) {
                                            return new LocomotiveMenu(containerId, inventory, loco);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<FreightMenu>> FREIGHT =
            TYPES.register(
                    "freight",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof FreightEntity freight) {
                                            return new FreightMenu(containerId, inventory, freight);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<TenderMenu>> TENDER =
            TYPES.register(
                    "tender",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof TenderEntity tender) {
                                            return new TenderMenu(containerId, inventory, tender);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<ZeppelinMenu>> ZEPPELIN =
            TYPES.register(
                    "zeppelin",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof ZeppelinEntity zeppelin) {
                                            return new ZeppelinMenu(containerId, inventory, zeppelin);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<TankMenu>> TANK =
            TYPES.register(
                    "tank",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof TankCartEntity tank) {
                                            return new TankMenu(containerId, inventory, tank);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<WorkCartMenu>> WORK_CART =
            TYPES.register(
                    "work_cart",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof WorkCartEntity cart) {
                                            return new WorkCartMenu(containerId, inventory, cart);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<TracksBuilderMenu>> TRACKS_BUILDER =
            TYPES.register(
                    "tracks_builder",
                    () ->
                            IMenuTypeExtension.create(
                                    (containerId, inventory, buffer) -> {
                                        int entityId = buffer.readVarInt();
                                        if (inventory.player.level().getEntity(entityId)
                                                instanceof TracksBuilderEntity builder) {
                                            return new TracksBuilderMenu(containerId, inventory, builder);
                                        }
                                        return null;
                                    }));

    public static final DeferredHolder<MenuType<?>, MenuType<traincraft.production.MachineMenu>> MACHINE =
            TYPES.register("machine", () -> IMenuTypeExtension.create((id, inventory, buffer) -> {
                var entity = inventory.player.level().getBlockEntity(buffer.readBlockPos());
                return entity instanceof traincraft.production.MachineBlockEntity machine
                        ? new traincraft.production.MachineMenu(id, inventory, machine) : null;
            }));

    private MenuRegistry() {}
}
