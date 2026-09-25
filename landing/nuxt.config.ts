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
      script: [
        {async: true, src: 'https://analytics.mateuszwozniak.com/js/pa-fA5oNLUBn-Og6e7bP8Q1O.js'},
        {innerHTML: 'window.plausible=window.plausible||function(){(plausible.q=plausible.q||[]).push(arguments)},plausible.init=plausible.init||function(i){plausible.o=i||{}};plausible.init()'},
      ],
    },
  },
})
