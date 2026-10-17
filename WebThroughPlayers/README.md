# Web Through Players (Fabric 1.21.11)

A small **client-side** Fabric mod. While a cobweb is held in the main hand, if your crosshair would hit another player, the mod substitutes a normal block raycast. This allows you to target a block through a player's entity hitbox.

## Requirements
- Minecraft Java Edition 1.21.11
- Fabric Loader for 1.21.11
- Fabric API for 1.21.11
- JDK 21
- Gradle (or a Gradle wrapper added by the Fabric template)

## Build
1. Install JDK 21.
2. Open this folder in IntelliJ IDEA, or install Gradle.
3. Run `gradle build` in this folder.
4. The output JAR should be in `build/libs/`, named similar to `web-through-players-1.0.0.jar`.
5. Put that JAR in your Minecraft instance's `mods` folder alongside Fabric API.

On Windows PowerShell, if Gradle is installed:
```powershell
gradle build
```

If you use IntelliJ IDEA, open the folder and allow it to import/sync the Gradle project, then run the `build` task from the Gradle tool window.

## Behavior and limitations
- Only changes crosshair targeting while a cobweb is held in the main hand.
- Other items keep normal entity targeting.
- It does not bypass block collision, reach limits, server-side checks, or anti-cheat rules.
- On multiplayer servers, the server still decides whether the placement is valid.
- This source project has not been compiled in this environment. If Gradle reports a version or mapping issue, use the official Fabric 1.21.11 template's current Loom/Loader/API versions and keep the code/mixin targeted at 1.21.11.

## Easiest build option (GitHub Actions)
This project includes `.github/workflows/build.yml`. Upload the project files to a GitHub repository, then open **Actions → Build mod → Run workflow** (or push to the `main` branch). When the workflow finishes, open its run and download the `web-through-players` artifact. The artifact contains the built JAR if compilation succeeds.
