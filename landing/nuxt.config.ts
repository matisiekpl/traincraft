import tailwindcss from '@tailwindcss/vite'

export default defineNuxtConfig({
  compatibilityDate: '2026-09-20',
  devtools: {enabled: false},
  css: ['~/assets/css/main.css'],
  vite: {
    plugins: [tailwindcss()],
  },
  nitro: {
    prerender: {
      routes: ['/', '/200.html'],
    },
  },
  app: {
    head: {
      htmlAttrs: {lang: 'en'},
      link: [
        {rel: 'icon', type: 'image/png', href: '/favicon.png'},
      ],
    },
  },
})
