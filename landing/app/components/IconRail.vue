<script setup lang="ts">
import {icons} from '~/data/site'
</script>

<template>
  <section class="bg-mc-bg-alt">
    <div class="mx-auto max-w-6xl px-4 py-12">
      <ul class="grid grid-cols-3 gap-4 sm:grid-cols-4 lg:grid-cols-6">
        <li v-for="icon in icons" :key="icon.file" class="pixel-card flex flex-col items-center gap-3 px-2 py-5">
          <img
            :src="`/icons/${icon.file}`"
            :alt="`${icon.name} item icon from Traincraft`"
            width="16"
            height="16"
            loading="lazy"
            class="pixelated size-12"
          />
          <span class="text-center text-xs text-mc-muted">{{ icon.name }}</span>
        </li>
      </ul>
    </div>
  </section>
</template>
