# NEXUS Android Starter — v0.2.0

A cyberpunk-themed Android UI prototype built with Kotlin and Jetpack Compose.

## What's new in v0.2
- App version updated to 0.2.0
- Clearer Steam demo state: the Connect control is a UI demonstration, not real authentication
- Cloud build workflow added to generate a debug APK with GitHub Actions

## Included screens
- Home dashboard
- Steam connection card (demo only)
- Game library placeholder
- Stream preferences: 720p / 1080p / 4K and 30 / 60 / 90 / 120 FPS
- Host status and disabled Start Stream action
- Settings

## Important: what this build does not do
This is not yet a functioning cloud-gaming service. It does not sign in to Steam, fetch a Steam library, launch PC games, or stream gameplay. Real Steam OpenID login needs a secure backend to validate the login response. Real PC game streaming needs a compatible gaming PC or GPU cloud host. Resolution/FPS choices are preferences only and do not guarantee performance.

## Build an APK using GitHub Actions (can be done from a phone)
1. Create a new GitHub repository.
2. Upload the contents of this project ZIP to the repository's `main` branch (extract the ZIP first).
3. Open the repository's **Actions** tab.
4. Select **Build NEXUS APK** and tap **Run workflow** (or wait for the automatic build after upload).
5. Open the completed workflow run, scroll to **Artifacts**, and download `NEXUS-debug-apk`.
6. Extract the artifact ZIP; install `app-debug.apk` on your Android phone. Android may ask you to allow installation from that browser/file manager.

## Build locally
Open this folder in Android Studio with JDK 17 and Android SDK 35, then run:
`gradle :app:assembleDebug`

The repository workflow uses Gradle 8.9, JDK 17, and Android SDK setup on GitHub-hosted runners.
