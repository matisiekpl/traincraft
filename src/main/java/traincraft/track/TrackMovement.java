package traincraft.track;

public final class TrackMovement {

    public interface Body {
        double x();

        double y();

        double z();

        void setPosition(double x, double y, double z);

        double motionX();

        double motionY();

        double motionZ();

        void setMotion(double x, double y, double z);

        void moveBy(double dx, double dy, double dz);

        double yOffset();

        float yaw();

        /** Locomotives are exempt from the slope's rolling assistance. */
        boolean isLocomotive();
    }

    private TrackMovement() {}

    /** Speed in blocks per tick, ignoring the vertical component. */
    public static double planarSpeed(Body body) {
        return Math.sqrt(body.motionX() * body.motionX() + body.motionZ() * body.motionZ());
    }

    /**
     * A straight piece, along X or along Z depending on the block metadata.
     *
     * <p>Height is pinned to {@code j + 0.2} first: track sits two tenths of a block proud of the
     * block it occupies, and letting the entity keep whatever Y it arrived with makes it climb or
     * sink as it crosses piece boundaries.
     *
     * @param cx the owning rail's block X, and {@code cz} its block Z -- the centreline to snap to
     */
    public static void moveOnStraight(Body body, int j, double cx, double cz, int meta) {
        double y = j + 0.2;
        double norm = planarSpeed(body);
        if (meta == 2 || meta == 0) {
            // Along Z: pin X to the centre of the rail column and put all the speed on Z.
            body.setPosition(cx + 0.5, y + body.yOffset(), body.z());
            body.setMotion(0.0, body.motionY(), Math.copySign(norm, body.motionZ()));
            body.moveBy(0.0, 0.0, Math.copySign(norm, body.motionZ()));
        } else if (meta == 1 || meta == 3) {
            body.setPosition(body.x(), y + body.yOffset(), cz + 0.5);
            body.setMotion(Math.copySign(norm, body.motionX()), body.motionY(), 0.0);
            body.moveBy(Math.copySign(norm, body.motionX()), 0.0, 0.0);
        }
    }

    /**
     * A quarter circle of radius {@code r} about {@code (cx, cz)}.
     *
     * <p>The body is put back onto the circle every tick rather than steered towards it: the
     * position is projected onto the radius and the velocity rotated to the tangent there. That
     * holds a train on a curve exactly, at any speed, without the drift an integrating solution
     * accumulates -- and it is why the curve centre is stored on the rail rather than inferred.
     */
    public static void moveOnTurn(Body body, int j, double r, double cx, double cz) {
        double y = j + 0.2;
        double cpx = body.x() - cx;
        double cpz = body.z() - cz;
        double cpNorm = Math.sqrt(cpx * cpx + cpz * cpz);
        double vnorm = planarSpeed(body);
        double normCpx = cpx / cpNorm;
        double normCpz = cpz / cpNorm;

        // Choose one sign for the whole tangent. Comparing each projected coordinate to
        // the uncorrected position confuses radial correction with travel and can reflect just
        // X or Z, steering a coupled cart (or bogie) across the track at low speed.
        double tangentX = -normCpz;
        double tangentZ = normCpx;
        double direction = Math.copySign(
                1.0, body.motionX() * tangentX + body.motionZ() * tangentZ);
        double vx2 = tangentX * vnorm * direction;
        double vz2 = tangentZ * vnorm * direction;

        double corrX = cx + cpx / cpNorm * r;
        double corrZ = cz + cpz / cpNorm * r;
        body.setPosition(corrX, y + body.yOffset(), corrZ);
        body.moveBy(vx2, 0.0, vz2);
        body.setMotion(vx2, body.motionY(), vz2);
    }

    public static void moveOnSlope(
            Body body, int j, double cx, double cz, double slopeAngle, int meta) {
        double norm = planarSpeed(body);

        double before = body.y();
        if (meta == 2 || meta == 0) {
            double foot = meta == 2 ? cz + 1 : cz;
            double y =
                    Math.abs(
                            j
                                    + Math.tan(slopeAngle * Math.abs(foot - body.z()))
                                    + body.yOffset()
                                    + 0.3);
            body.setPosition(cx + 0.5, y, body.z());
            body.moveBy(0.0, 0.0, Math.copySign(norm, body.motionZ()));
            norm = rollingAssistance(body, norm, before);
            if (!body.isLocomotive() && norm < 0.01) {
                if (body.motionZ() < 0.0 && meta == 2) {
                    norm += 1.0E-4;
                    body.setMotion(body.motionX(), body.motionY(), Math.abs(body.motionZ()));
                }
                if (body.motionZ() > 0.0 && meta == 0) {
                    norm += 1.0E-4;
                    body.setMotion(body.motionX(), body.motionY(), -Math.abs(body.motionZ()));
                }
            }
            body.setMotion(0.0, 0.0, Math.copySign(norm, body.motionZ()));
        } else if (meta == 1 || meta == 3) {
            double foot = meta == 1 ? cx + 1 : cx;
            double y = j + Math.tan(slopeAngle * Math.abs(foot - body.x())) + body.yOffset() + 0.3;
            body.setPosition(body.x(), y, cz + 0.5);
            body.moveBy(Math.copySign(norm, body.motionX()), 0.0, 0.0);
            norm = rollingAssistance(body, norm, before);
            if (!body.isLocomotive() && norm < 0.01) {
                if (body.motionX() < 0.0 && meta == 1) {
                    norm += 1.0E-4;
                    body.setMotion(Math.abs(body.motionX()), body.motionY(), body.motionZ());
                }
                if (body.motionX() > 0.0 && meta == 3) {
                    norm += 1.0E-4;
                    body.setMotion(-Math.abs(body.motionX()), body.motionY(), body.motionZ());
                }
            }
            body.setMotion(Math.copySign(norm, body.motionX()), 0.0, 0.0);
        }
    }

    /** Gravity on a ramp: two percent gained downhill, two lost uphill, nothing on the level. */
    private static double rollingAssistance(Body body, double norm, double yBefore) {
        if (body.isLocomotive()) {
            return norm;
        }
        double delta = body.y() - yBefore;
        if (delta < 0.0) {
            return norm * 1.02;
        }
        if (delta > 0.0) {
            return norm * 0.98;
        }
        return norm;
    }

    /**
     * A crossing. Whichever axis the body faces is the one it keeps moving along and the other is
     * dropped, so a train drives straight through instead of being deflected onto the other line.
     */
    public static void moveOnCrossing(Body body, int j) {
        body.setPosition(body.x(), j + 0.2 + body.yOffset(), body.z());
        if (Math.abs(body.motionX()) >= Math.abs(body.motionZ())) {
            body.moveBy(body.motionX(), 0.0, 0.0);
        } else {
            body.moveBy(0.0, 0.0, body.motionZ());
        }
    }

    /** Whether the block at these coordinates is one a powered rail can push off. */
    public interface Solid {
        boolean at(int x, int y, int z);
    }

    /**
     * Upstream's {@code matrix}: the two ends every vanilla rail shape connects, indexed by the
     * shape's ordinal. Identical, row for row, to {@code AbstractMinecart.exits}; kept here so this
     * class stays free of Minecraft and testable on its own.
     */
    private static final int[][][] EXITS = {
        {{0, 0, -1}, {0, 0, 1}},
        {{-1, 0, 0}, {1, 0, 0}},
        {{-1, -1, 0}, {1, 0, 0}},
        {{-1, 0, 0}, {1, -1, 0}},
        {{0, 0, -1}, {0, -1, 1}},
        {{0, -1, -1}, {0, 0, 1}},
        {{0, 0, 1}, {1, 0, 0}},
        {{0, 0, 1}, {-1, 0, 0}},
        {{0, 0, -1}, {-1, 0, 0}},
        {{0, 0, -1}, {1, 0, 0}}
    };

    /**
     * One tick along a piece of vanilla rail.
     *
     * <p>Community Edition's {@code EntityRollingStock.updateOnTrack}, the branch it takes when the
     * block under the stock is a Minecraft rail rather than one of Traincraft's own. It is not the
     * same code as the track pieces above: it snaps to the rail's chord rather than to a centreline,
     * it reads the two exits from the shape, and it is where a locomotive taken through a curve
     * faster than it should be comes off.
     *
     * @param railIndex the rail shape's ordinal, upstream's {@code i1}
     * @param railFunctions upstream's {@code shouldDoRailFunctions}: false for a bogie, which is
     *     dragged over the rail rather than working it
     * @param fullBody false for a bogie, which takes neither the slope's nudge nor air drag
     * @param railDrag the factor {@code moveMinecartOnRail} applies before the clamp
     * @param canDerail true for a locomotive and for anything running on a second bogie
     * @return true when the curve threw the body off, which is what breaks its couplings
     */
    public static boolean moveOnVanillaRail(
            Body body,
            int i,
            int j,
            int k,
            int railIndex,
            boolean poweredRail,
            boolean powered,
            boolean railFunctions,
            boolean fullBody,
            double maxSpeed,
            double railDrag,
            double dragAir,
            boolean canDerail,
            double derailSpeed,
            Solid solid) {
        double yStart = body.y();
        double px = body.x();
        double py = j;
        double pz = body.z();

        if (railIndex >= 2 && railIndex <= 5) {
            py = j + 1;
        }

        if (fullBody && !body.isLocomotive()) {
            double nudge = -0.002;
            switch (railIndex) {
                case 2 -> body.setMotion(body.motionX() - nudge, body.motionY(), body.motionZ());
                case 3 -> body.setMotion(body.motionX() + nudge, body.motionY(), body.motionZ());
                case 4 -> body.setMotion(body.motionX(), body.motionY(), body.motionZ() + nudge);
                case 5 -> body.setMotion(body.motionX(), body.motionY(), body.motionZ() - nudge);
                default -> {}
            }
        }

        int[][] exits = EXITS[railIndex];
        double d9 = exits[1][0] - exits[0][0];
        double d10 = exits[1][2] - exits[0][2];
        double d11 = Math.sqrt(d9 * d9 + d10 * d10);
        if (body.motionX() * d9 + body.motionZ() * d10 < 0.0) {
            d9 = -d9;
            d10 = -d10;
        }

        double d13 = planarSpeed(body);
        body.setMotion(d13 * d9 / d11, body.motionY(), d13 * d10 / d11);

        // An unpowered golden rail is a brake, and only for something that works the rail.
        if (poweredRail && !powered && railFunctions) {
            if (planarSpeed(body) < 0.03) {
                body.setMotion(0.0, 0.0, 0.0);
            } else {
                body.setMotion(body.motionX() * 0.5, 0.0, body.motionZ() * 0.5);
            }
        }

        double d18 = i + 0.5 + exits[0][0] * 0.5;
        double d19 = k + 0.5 + exits[0][2] * 0.5;
        double d20 = i + 0.5 + exits[1][0] * 0.5;
        double d21 = k + 0.5 + exits[1][2] * 0.5;
        d9 = d20 - d18;
        d10 = d21 - d19;
        double d17;
        if (d9 == 0.0) {
            px = i + 0.5;
            d17 = pz - k;
        } else if (d10 == 0.0) {
            pz = k + 0.5;
            d17 = px - i;
        } else {
            d17 = ((px - d18) * d9 + (pz - d19) * d10) * 2.0;
        }

        // The curve, taken too fast: the chord it is put back onto is deliberately wrong, and the
        // body leaves the rail along it. Upstream computes the wrong chord and says nothing else.
        boolean derailed = false;
        if (canDerail && d13 > derailSpeed && railIndex >= 6) {
            if (d9 > 0.0 && d10 < 0.0) {
                d10 = 0.0;
                d9 += 2.0;
            } else if (d9 < 0.0 && d10 > 0.0) {
                d9 = 0.0;
                d10 += 2.0;
            } else if (d10 < 0.0 && d9 < 0.0) {
                d10 -= 2.0;
                d9 = 0.0;
            } else if (d9 > 0.0 && d10 > 0.0) {
                d10 += 2.0;
                d9 = 0.0;
            }
            derailed = true;
        }

        px = d18 + d9 * d17;
        pz = d19 + d10 * d17;
        body.setPosition(px, py + body.yOffset() + 0.35, pz);

        double mx = Math.clamp(body.motionX() * railDrag, -maxSpeed, maxSpeed);
        double mz = Math.clamp(body.motionZ() * railDrag, -maxSpeed, maxSpeed);
        body.setMotion(mx, body.motionY(), mz);
        body.moveBy(mx, 0.0, mz);

        px = body.x();
        py = body.y();
        pz = body.z();
        if (exits[0][1] != 0
                && (int) Math.floor(px) - i == exits[0][0]
                && (int) Math.floor(pz) - k == exits[0][2]) {
            py += exits[0][1];
            body.setPosition(px, py, pz);
        } else if (exits[1][1] != 0
                && (int) Math.floor(px) - i == exits[1][0]
                && (int) Math.floor(pz) - k == exits[1][2]) {
            py += exits[1][1];
            body.setPosition(px, py, pz);
        }

        if (fullBody) {
            body.setMotion(body.motionX() * dragAir, 0.0, body.motionZ() * dragAir);
        }

        // Height lost since the tick began is handed back as speed, for everything but a locomotive.
        double d28 = fullBody && !body.isLocomotive() ? (yStart - body.y()) * 0.05 : 0.0;
        double d14 = planarSpeed(body);
        if (d14 > 0.0) {
            body.setMotion(
                    body.motionX() / d14 * (d14 + d28),
                    body.motionY(),
                    body.motionZ() / d14 * (d14 + d28));
        }

        body.setPosition(px, body.y() + body.yOffset() - 0.8, pz);

        int k1 = (int) Math.floor(px);
        int l1 = (int) Math.floor(pz);
        if (k1 != i || l1 != k) {
            double d15 = planarSpeed(body);
            body.setMotion(d15 * (k1 - i), body.motionY(), d15 * (l1 - k));
        }

        if (powered && railFunctions) {
            double d31 = planarSpeed(body);
            if (d31 > 0.01) {
                body.setMotion(
                        body.motionX() + body.motionX() / d31 * 0.06,
                        body.motionY(),
                        body.motionZ() + body.motionZ() / d31 * 0.06);
            } else if (railIndex == 1) {
                if (solid.at(i - 1, j, k)) {
                    body.setMotion(0.02, body.motionY(), body.motionZ());
                } else if (solid.at(i + 1, j, k)) {
                    body.setMotion(-0.02, body.motionY(), body.motionZ());
                }
            } else if (railIndex == 0) {
                if (solid.at(i, j, k - 1)) {
                    body.setMotion(body.motionX(), body.motionY(), 0.02);
                } else if (solid.at(i, j, k + 1)) {
                    body.setMotion(body.motionX(), body.motionY(), -0.02);
                }
            }
        }
        return derailed;
    }

    public static void limitSpeed(Body body, double maxSpeed, boolean drag) {
        double factor = drag ? 0.9 : 1.0;
        double mx = Math.clamp(body.motionX() * factor, -maxSpeed, maxSpeed);
        double mz = Math.clamp(body.motionZ() * factor, -maxSpeed, maxSpeed);
        body.setMotion(mx, body.motionY(), mz);
    }
}
