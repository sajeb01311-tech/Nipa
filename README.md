# Nipa AI (Android) — Phase 2

Status: **Phase 2 complete (code written, NOT compiled or tested).** Phase 1 scaffold + premium main screen,
mode selector, input bar, settings dialog and a procedural-motion avatar framework.

**Build status: never built.** The authoring environment had no Android SDK. Expect to fix small
compile errors on the first build. Unit tests are written but have not been run.

## What works (by design, once it compiles)
- Main screen with Nipa's portrait as the focus, soft pink/purple theme, dark/light/system theme.
- Mode selector: Friend, Girlfriend, Assistant (label, accent colour, greeting, avatar reaction only).
- Text field + send, microphone button, stop button, settings (reduce motion, theme), status pill.
- UI states: Idle, Listening, Thinking, Speaking (+ Happy/Curious/Reassuring/Error available) with animated indicators.
- Avatar: breathing, tiny sway/tilt, rim glow, expression-badge cross-fade. Reduced-motion disables all looping animation.
- English + Bengali UI strings, TalkBack labels, landscape layout.

## What is NOT real yet
- **No microphone, speech recognition, AI, TTS, memory or commands.** Pressing send/mic runs a *timed demo*
  (Thinking → Speaking → Idle). The status pill says "Demo mode".
- The avatar is **not rigged**: no blinking, no mouth movement, no lip-sync. See `docs/ASSET_PIPELINE.md`.
- Mode and settings are in memory only (not saved between launches).
- Adult-oriented mode is not present (needs age gate; Phase 5). No history button yet (Phase 7).
- No permissions requested.

## Stack
Kotlin 1.9.24, AGP 8.5.2, Gradle 8.7, Compose BOM 2024.06.00, Material 3, minSdk 26, compile/target 34, JDK 17.
New deps in Phase 2: `material-icons-core`, `lifecycle-viewmodel-compose` 2.8.3, `lifecycle-runtime-compose` 2.8.3.

## Build
1. Open `NipaAI` in Android Studio (SDK 34, JDK 17) and sync. Let it generate the wrapper jar/scripts.
2. `Build > Build APK(s)` or `gradle assembleDebug` → `app/build/outputs/apk/debug/app-debug.apk`.
3. Unit tests: `gradle testDebugUnitTest` (not yet run).

Phone-only: push to GitHub and build with GitHub Actions (`actions/setup-java` + `android-actions/setup-android`).
Not verified by me; ask and I'll write the workflow.

## Project layout (Phase 2 additions)
```
app/src/main/java/com/nipa/ai/
  MainActivity.kt                 (modified)
  ai/ConnectionStatus.kt
  avatar/{AvatarState, AvatarExpression, MotionProfile, AvatarController,
          AvatarRenderer, LayeredImageAvatarRenderer}.kt
  modes/NipaMode.kt
  settings/ThemeMode.kt
  ui/theme/Theme.kt               (modified)
  ui/home/{HomeUiState, HomeViewModel, HomeRoute, HomeScreen,
           HomeComponents, StateIndicators, SettingsDialog}.kt
app/src/main/res/drawable-nodpi/  nipa_portrait.jpg, nipa_expr_*.jpg (13 images)
app/src/main/res/values{,-bn}/strings.xml
app/src/test/java/com/nipa/ai/avatar/AvatarMappingTest.kt
docs/ASSET_PIPELINE.md
```
