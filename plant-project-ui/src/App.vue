<script setup>
  import { RouterLink, RouterView } from "vue-router";
  // import Header from "./components/commons/Header.vue";
  import Toast from "./components/commons/Toast.vue";
</script>

<template>
  <header>
    <RouterView name="navbar" />
  </header>
  <main class="container-xl">
    <RouterView />
  </main>
  <div><Toast id="toast-global" /></div>
</template>
