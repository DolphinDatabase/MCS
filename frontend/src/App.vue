<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <RouterView />
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nunito&family=Roboto&display=swap');

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
  font-family: 'Nunito', sans-serif;
}

#app{
  height: 100vh;
}

#page{
    height: 100%;
    width: 100%;
    display: flex;
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

.el-table__expand-icon {
    position: relative;
    cursor: pointer;
    color: var(--el-text-color-regular);
    font-size: 12px;
    transition: transform var(--el-transition-duration-fast) ease-in-out;
    height: 20px;
    transform: rotate(90deg)
}
.el-table__expand-icon.el-table__expand-icon--expanded {
    transform: rotate(270deg);
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg,  rgb(125, 214, 246), rgb(26, 167, 236));
  color: #fff;
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #4ADEDE, #7DD6F6);
  right: 0;
}

.router-link-exact-active{
  background-color: rgb(125, 214, 246);
  display: flex;
  border-radius: 50em;
  transition: 0.5s;
  align-items: center;
  justify-content: center;
  width: 80px;
  height: 50px;
}

</style>
