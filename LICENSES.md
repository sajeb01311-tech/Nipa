# Licenses (Phase 2)

| Component | License |
|---|---|
| AndroidX / Jetpack Compose / Material 3 / Material icons core / Lifecycle | Apache 2.0 |
| Kotlin / kotlinx.coroutines | Apache 2.0 |
| JUnit 4 | EPL 1.0 |

Later phases will add: Room, DataStore, OkHttp (Apache 2.0), FastAPI (MIT), and the avatar runtime.

## Avatar runtime
Phase 2 uses a custom Compose renderer (no third-party avatar SDK, no extra licence).
Options evaluated for later (see `docs/ASSET_PIPELINE.md`):
- Live2D Cubism SDK: free for small businesses/individuals under revenue thresholds, otherwise paid; check current terms.
- Rive: runtime is MIT; editor has free and paid tiers.
- Spine: runtime requires a purchased Spine license.

## Character art
`nipa_portrait.jpg` and `nipa_expr_*.jpg` are crops of the reference image supplied by the project owner.
You are responsible for confirming you hold the rights to this image and that the generator's terms (if it was
AI-generated) allow your intended use before publishing the app.
