<script setup lang="ts">
import {ref} from 'vue'
import {gallery} from '~/data/site'

const dialog = ref<HTMLDialogElement | null>(null)
const active = ref<typeof gallery[number] | null>(null)

function open(shot: typeof gallery[number]) {
  active.value = shot
  dialog.value?.showModal()
}
</script>

<template>
  <section id="screens" class="mx-auto max-w-6xl px-4 py-20">
    <h2 class="pixel-font text-base text-white sm:text-xl">Screens from Minecraft 26.2</h2>

    <div class="mt-10 grid gap-6 lg:grid-cols-2">
      <button
        v-for="shot in gallery"
        :key="shot.file"
        type="button"
        class="pixel-card cursor-pointer p-2"
        @click="open(shot)"
      >
        <img
          :src="`/screenshots/${shot.file}@800.webp`"
          :srcset="`/screenshots/${shot.file}@800.webp 800w, /screenshots/${shot.file}.webp 1600w`"
          sizes="(min-width: 1024px) 50vw, 100vw"
          :alt="shot.alt"
          :width="shot.width"
          :height="shot.height"
          loading="lazy"
          class="block w-full"
        />
      </button>
    </div>

    <dialog
      ref="dialog"
      class="m-auto max-w-[min(1600px,94vw)] bg-transparent p-0 backdrop:bg-black/80"
      @click="dialog?.close()"
    >
      <img
        v-if="active"
        :src="`/screenshots/${active.file}.webp`"
        :alt="active.alt"
        :width="active.width"
        :height="active.height"
        class="block h-auto w-full"
      />
    </dialog>
  </section>
</template>
