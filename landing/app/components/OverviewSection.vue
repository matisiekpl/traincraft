<script setup lang="ts">
import {overview} from '~/data/site'
</script>

<template>
  <section id="overview" class="mx-auto max-w-6xl px-4 py-20">
    <h2 class="pixel-font text-base text-white sm:text-xl">What the NeoForge port includes</h2>

    <div class="mt-10 max-w-3xl space-y-6">
      <p v-for="paragraph in overview" :key="paragraph" class="text-lg text-mc-text">{{ paragraph }}</p>
    </div>
  </section>
</template>
