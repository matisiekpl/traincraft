<script setup lang="ts">
import {site} from '~/data/site'

const title = 'Traincraft 26.2 — Traincraft for Minecraft 26.2 on NeoForge'
const description =
  'Traincraft for Minecraft 26.2: a NeoForge port of the 1.7.10 Community Edition, with steam, diesel and electric locomotives, rolling stock, prefabricated track, signals and a fuel chain.'
const image = `${site.url}/og.jpg`

useSeoMeta({
  title,
  description,
  ogType: 'website',
  ogSiteName: site.name,
  ogTitle: 'Traincraft on Minecraft 26.2 — NeoForge port',
  ogDescription: description,
  ogUrl: `${site.url}/`,
  ogImage: image,
  ogImageWidth: 1200,
  ogImageHeight: 630,
  ogImageType: 'image/jpeg',
  ogImageAlt: 'Traincraft locomotives on ballast track in Minecraft 26.2',
  ogLocale: 'en_US',
  twitterCard: 'summary_large_image',
  twitterTitle: 'Traincraft on Minecraft 26.2 — NeoForge port',
  twitterDescription: description,
  twitterImage: image,
})

useHead({
  link: [{rel: 'canonical', href: `${site.url}/`}],
  script: [
    {
      type: 'application/ld+json',
      innerHTML: JSON.stringify({
        '@context': 'https://schema.org',
        '@type': 'VideoGame',
        name: 'Traincraft for Minecraft 26.2',
        alternateName: 'Traincraft 26.2',
        description,
        url: `${site.url}/`,
        image,
        applicationCategory: 'GameApplication',
        gamePlatform: `Minecraft ${site.minecraftVersion}`,
        operatingSystem: 'Windows, macOS, Linux',
        author: {'@type': 'Person', name: 'Mateusz Woźniak'},
        creator: [
          {'@type': 'Person', name: 'Spitfire4466'},
          {'@type': 'Person', name: 'Mrbrutal'},
        ],
        keywords: 'traincraft 26.2, traincraft 26, traincraft neoforge, minecraft 26.2 train mod, traincraft port',
        softwareVersion: '0.1.0',
        codeRepository: site.githubUrl,
      }),
    },
  ],
})
</script>

<template>
  <main>
    <HeroSection/>
    <div class="ballast-strip"></div>
    <IconRail/>
    <ScreenshotGallery/>
    <CreditsSection/>
    <DownloadSection/>
    <SiteFooter/>
  </main>
</template>
