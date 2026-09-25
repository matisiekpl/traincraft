<script setup lang="ts">
import {site} from '~/data/site'
</script>

<template>
  <footer class="bg-mc-bg-alt">
    <div class="ballast-strip"></div>
    <div class="mx-auto flex max-w-6xl flex-col gap-3 px-4 py-10 text-sm text-mc-muted">
      <p>Traincraft on Minecraft {{ site.minecraftVersion }}, NeoForge {{ site.neoforgeVersion }}. Ported by Mateusz Woźniak.</p>
      <p>Traincraft is a Minecraft mod. Not affiliated with Mojang Studios or Microsoft.</p>
    </div>
  </footer>
</template>
