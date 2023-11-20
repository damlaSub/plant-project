<script setup>
  import { RouterLink, RouterView } from "vue-router";
  import Toast from "./components/commons/Toast.vue";
</script>

<template>
  <header></header>
  <main class="container-xl">
    <RouterView />
  </main>
  <div><Toast id="toast-global" /></div>
</template>
