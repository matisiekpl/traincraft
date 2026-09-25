export {}

declare global {
  interface Window {
    plausible: (event: string) => void
  }
}
