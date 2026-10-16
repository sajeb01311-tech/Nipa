# Nipa character asset pipeline

## What the app does today (Phase 2) — honest summary
- Your reference sheet is **one flat image**. It has no separate layers (hair, eyes, eyelids, mouth, body).
- The app uses two kinds of crops from it:
  - `nipa_portrait.jpg` — the main front-facing illustration (text/UI overlays cropped away).
  - `nipa_expr_*.jpg` — 12 small face tiles (Happy, Smile, Cute, Shy, Thinking, Sad, Surprised, Angry, Idle, Listening, Talking, Laughing).
- `LayeredImageAvatarRenderer` applies **procedural motion to the whole portrait** (breathing scale, tiny sway/tilt, drift, coloured rim glow) and cross-fades an **expression badge** between the face tiles.
- It does **not** blink, move the mouth, or change the face. It is **not rigged** and **not lip-synced**. The name "layered" refers to the renderer being the place where real layers plug in later.
- Quality limit: the face tiles are only ~115×152 px and the portrait ~316×770 px, so they look soft when enlarged. Supply the original full-resolution artwork for a sharp result.

## How to integrate / replace the character image
1. Put images in `app/src/main/res/drawable-nodpi/` (lowercase, `a-z0-9_` only, e.g. `nipa_portrait.jpg`).
2. To swap the main portrait: overwrite `nipa_portrait.jpg` (keep the name), or change `R.drawable.nipa_portrait` in `LayeredImageAvatarRenderer.kt`.
3. To swap/add expression frames: add `nipa_expr_<name>.jpg` and add an entry in `AvatarExpression.kt`, then map it in `AvatarExpression.forState`.
4. Keep portrait roughly 3:4 or taller; the renderer top-aligns and crops, so the face must stay near the top.
5. Rebuild.

## Required assets for REAL facial animation
Export from one master file (e.g. 3000×4000 px, PSD/KRA), same canvas size, transparent background, every layer aligned to the same origin:

| Group | Layers |
|---|---|
| Body | torso, neck, shoulders/arms, clothing (per outfit) |
| Hair | back hair, side-hair L/R, front fringe, ribbon/accessory |
| Head | face base (no eyes/brows/mouth), ears, blush overlay |
| Eyes (each side) | eye white, iris, pupil, highlights, upper lash, lower lash |
| Eyelids | open, half-closed, closed (blink frames) |
| Eyebrows | neutral, raised, worried, angry, soft (each side) |
| Mouth | closed-smile, closed-neutral, A, I, U, E, O, M/B/P, F/V, wide-laugh, small-sad |
| Extras | tear, sweat-drop, heart effects, shadow layer |

Expression states to cover: Idle, Blinking, Listening, Thinking, Speaking, Smiling, Happy, Curious, Shy, Reassuring, Sad, Surprised, Laughing.

## How to separate layers from a flat image
- **Krita (free)**, Clip Studio Paint, or Photoshop: trace each part onto its own layer and **repaint the hidden areas** (the hair behind the face, the eye under the eyelid, the mouth interior). This hand-painting is the real work; auto-cut alone leaves holes.
- AI helpers (Photoshop Generative Fill, inpainting models) can fill hidden regions, but check identity consistency by eye.
- Do not use the 18+/lingerie reference tiles for the app UI; they are intentionally not cropped into this project.

## Prompts for generating missing assets (image-to-image with your reference as input)
Use your generator's "reference image / character reference" feature so identity stays fixed. Example:

> Same character as the reference: young woman, long dark-brown hair with soft bangs, large brown eyes, soft features, warm smile, pink bow hair accessory, pastel pink aesthetic. Front-facing, symmetrical head-on view, neutral lighting, plain flat background. Expression: **eyes fully closed, relaxed** (blink frame). Keep exact face shape, hairstyle, proportions and colours. No text.

Repeat with the changed part only: *half-closed eyes*, *mouth open "A" viseme*, *mouth "O"*, *raised eyebrows*, etc. Always request a plain background so layers can be cut cleanly.

## Runtime options (decision for Phase 3)
| Option | Android | Cost / licence | Asset need | Notes |
|---|---|---|---|---|
| **Custom Compose 2D puppet** (recommended next) | Native | None | Layered PNGs | Full control, light on battery/RAM; I build the rig code |
| Live2D Cubism | Official SDK | Free tier under revenue limits, otherwise paid; check current terms | Rigged `.moc3` model | Best quality; needs Cubism Editor work |
| Rive | Official runtime | Runtime MIT; editor free/paid tiers | `.riv` file made in Rive | Good for 2D vector/mesh; layered raster support is limited |
| Spine | Official runtime | Paid licence required | Spine project | Strong 2D skeletal animation |
| 3D avatar | Varies | Varies | 3D model | Heavy; not matching the 2D art |

Next step for real animation: provide the layer set above, then Phase 3 implements the rig (blink, mouth shapes, head turn) behind the existing `AvatarRenderer` interface.
