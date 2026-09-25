# Landing page

Static one-page site for the Traincraft 26.2 port. Nuxt 4, Tailwind v4, no server runtime.

```
yarn install
yarn dev        # http://localhost:3000
yarn generate   # static output in .output/public
```

## Editing

`app/data/site.ts` holds every link, number and piece of section copy:

- `url` — feeds the canonical link, `og:url` and `og:image`; also hardcoded in `public/robots.txt`
  and `public/sitemap.xml`.
- `released` — `false` renders the download button disabled with the line "The port is not
  published yet." Set it to `true` and point `downloadUrl` at the release to switch it on.
- `stats`, `features`, `credits`, `icons`, `gallery` — the content of each section.

Page title, description, OpenGraph tags and the JSON-LD block live in `app/pages/index.vue`.

## Assets

`public/screenshots/` holds WebP at two widths per shot (`name.webp` and `name@800.webp`, hero at
`hero.webp` / `hero@1200.webp`). Regenerate from a PNG with:

```
magick <source>.png -resize 1600x -strip -quality 80 public/screenshots/<name>.webp
magick <source>.png -resize 800x  -strip -quality 78 public/screenshots/<name>@800.webp
```

`public/og.jpg` is 1200x630, built from the hero screenshot with a Press Start 2P title composited
over it. `public/icons/` holds 16x16 item icons copied from
`../src/main/resources/assets/tc/textures/item/trains/`, rendered at 48px with
`image-rendering: pixelated`.

## Publishing

`docs/LICENSING.md` in this repository forbids publishing the port without written permission from
the Traincraft team. That applies to this page as much as to the jar.
