# Traincraft

Minecraft 26.2 mod built with NeoForge and Java 25.

Port of Traincraft Community Edition from Minecraft 1.7.10 to 26.2.

Landing page and download: [traincraft.mateuszwozniak.com](https://traincraft.mateuszwozniak.com)

![A diesel locomotive and a shunter at a passing loop, with the cab HUD showing speed, heat and fuel](landing/public/screenshots/cab-hud.webp)

![Track, signals and a station in a village, with a diesel locomotive and a tank train on the main line](landing/public/screenshots/overview.webp)

## Source Sets

- `main`: railway gameplay, vehicle simulation, client rendering and bundled definitions.
- `development`: GameTests and scripted client scenarios, excluded from the published JAR.
- `test`: unit tests and resource contract tests.
- `tools`: capture orchestration, image comparison and resource generators.

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

## Credits

- Port to Minecraft 26.2 and NeoForge: Mateusz Woźniak
- Traincraft: Spitfire4466 and Mrbrutal
- 1.7.10 Community Edition: EternalBlueFlame and NitroxydeX

The locomotives, rolling stock, models, textures and track designs come from the original
Traincraft and its Community Edition.

- [traincraft-mod.com](http://www.traincraft-mod.com)
- [CurseForge project](https://www.curseforge.com/minecraft/mc-mods/traincraft)
- [Traincraft Discord](https://discord.gg/SgpnCnK)
