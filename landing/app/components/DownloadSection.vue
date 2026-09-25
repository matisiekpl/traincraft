<script setup lang="ts">
import {site} from '~/data/site'
</script>

<template>
  <section id="download" class="mx-auto max-w-6xl px-4 py-20">
    <h2 class="pixel-font text-base text-white sm:text-xl">
      Download Traincraft for Minecraft {{ site.minecraftVersion }}
    </h2>

    <div class="pixel-card mt-10 flex flex-col gap-8 p-8 sm:p-10">
      <p class="max-w-2xl text-base text-mc-text">
        Minecraft {{ site.minecraftVersion }} with NeoForge {{ site.neoforgeVersion }} or newer.
        JEI is optional and adds the Assembly Table recipes. The jar goes into the mods folder.
      </p>

      <div class="flex flex-wrap items-center gap-4">
        <a
          v-if="site.released"
          :href="site.downloadUrl"
          class="pixel-btn pixel-btn-primary px-7 py-4 text-xs sm:text-sm"
          rel="noopener"
        >Download the jar</a>
        <button v-else type="button" class="pixel-btn px-7 py-4 text-xs sm:text-sm" disabled>
          Download the jar
        </button>

        <a :href="site.issuesUrl" class="pixel-btn px-7 py-4 text-xs sm:text-sm" rel="noopener">Report a bug</a>
      </div>

      <p v-if="!site.released" class="text-sm text-mc-muted">The port is not published yet.</p>
    </div>
  </section>
</template>
