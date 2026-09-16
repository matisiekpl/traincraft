# Traincraft

Minecraft 26.2 mod built with NeoForge and Java 25.

## Source Sets

- `main`: railway gameplay, vehicle simulation, client rendering and bundled definitions.
- `development`: GameTests and scripted client scenarios, excluded from the published JAR.
- `test`: unit tests and resource contract tests.
- `tools`: capture orchestration, image comparison and resource generators.

## Architecture

`traincraft.track` owns placement plans and track geometry. `TrackPlacer` applies plans to
the world; `TrackBreaker` removes only blocks belonging to the selected assembly.
Plan offsets are relative to the placement origin. Owner-relative offsets are calculated
by `TrackPlan`, using the same owning rail selected during placement.

`traincraft.vehicle` separates entity integration from fuel, boiler, control and inventory
state. Client rendering consumes per-entity animation state and immutable model geometry.
`traincraft.bootstrap` registers gameplay content; development registration is separate.

Track definitions live in `data/tc/track_definitions.json` and
`data/tc/track_placements.json`. Vehicle definitions live in `data/tc/vehicles.json`.
Vehicle meshes live in `assets/tc/vehicle_models`.

## Verification

Run from this directory:

```sh
./gradlew test compileDevelopmentJava compileToolsJava runGameTestServer jar
./gradlew trackitems
./gradlew runClient
```

`trackitems` generates item resources from the track catalog. GameTests cover placement
and removal of every catalog plan, adjacent assemblies, blocked placement, switching
and locomotive operation. Unit tests do not replace visual checks in the client.
