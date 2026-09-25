<script setup lang="ts">
import {site} from '~/data/site'
</script>

<template>
  <section class="relative isolate overflow-hidden">
    <img
      src="/screenshots/hero.webp"
      srcset="/screenshots/hero@1200.webp 1200w, /screenshots/hero.webp 2400w"
      sizes="100vw"
      width="2400"
      height="1005"
      alt="Traincraft on Minecraft 26.2: a diesel locomotive and a red shunter on ballast track next to a village"
      class="absolute inset-0 size-full object-cover"
      fetchpriority="high"
    />
    <div class="absolute inset-0 scrim"></div>

    <div class="relative mx-auto flex max-w-5xl flex-col gap-8 px-4 py-28 sm:py-36 lg:py-44">
      <h1 class="pixel-font max-w-4xl text-[clamp(1.25rem,4.6vw,3.25rem)] text-white drop-shadow-[3px_3px_0_rgba(0,0,0,0.6)]">
        Traincraft on Minecraft {{ site.minecraftVersion }}
      </h1>

      <p class="max-w-2xl text-lg text-mc-text drop-shadow-[2px_2px_0_rgba(0,0,0,0.8)] sm:text-xl">
        A NeoForge port of Traincraft Community Edition, moved from Minecraft 1.7.10 to {{ site.minecraftVersion }}.
      </p>

      <div class="flex flex-wrap gap-4">
        <a href="#download" class="pixel-btn pixel-btn-primary px-7 py-4 text-xs sm:text-sm">Download</a>
        <a :href="site.githubUrl" class="pixel-btn px-7 py-4 text-xs sm:text-sm" rel="noopener">Source on GitHub</a>
      </div>
    </div>
  </section>
</template>
