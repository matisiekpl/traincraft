<script setup lang="ts">
import {credits, originalLinks} from '~/data/site'
</script>

<template>
  <section id="credits" class="bg-mc-bg-alt">
    <div class="mx-auto max-w-6xl px-4 py-20">
      <h2 class="pixel-font text-base text-white sm:text-xl">Credits</h2>

      <dl class="mt-10 grid gap-6 md:grid-cols-3">
        <div v-for="credit in credits" :key="credit.role" class="pixel-card p-7">
          <dt class="text-sm text-mc-muted">{{ credit.role }}</dt>
          <dd class="pixel-font mt-4 text-sm leading-relaxed text-mc-amber">{{ credit.names }}</dd>
        </div>
      </dl>

      <ul class="mt-10 flex flex-wrap gap-x-8 gap-y-3">
        <li v-for="link in originalLinks" :key="link.href">
          <a
            :href="link.href"
            class="text-mc-amber underline decoration-2 underline-offset-4 hover:text-white"
            rel="noopener"
          >{{ link.label }}</a>
        </li>
      </ul>
    </div>
  </section>
</template>
