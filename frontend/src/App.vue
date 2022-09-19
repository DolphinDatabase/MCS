<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <RouterView />
</template>

<style>

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
}

body{
  background-color: var(--color-bg);
  display: flex;
}

#app{
  
  display:flex;
  align-items:center;
  justify-content:center;
  flex-direction: column;
  height: 100vh;
  scroll-behavior: smooth;
  flex: 1 1 auto;
}

:root {
  --color-bg: #DBE8F4;
  --color-bg-variant: #C2E2FF;
  --color-primary: #0916A3;
  --color-primary-variant: #3362CC;
  --color-white: #fff;
  --color-light: #407BFF;

  --color-red: #E86161;
  --color-green: #30B565;
  --color-orange: #F0AF09;

  --transition: all 400ms ease;

  --container-width-lg: 75%;
  --container-width-md: 86%;
  --container-width-sm: 90%;
}

.column {
  display: flex;
  flex-direction: column;
}

.row {
  display: flex;
  flex-direction: row;
} 

</style>
