<script setup>
  import { RouterLink, RouterView } from "vue-router";
  import Toast from "./components/commons/Toast.vue";
  import Tooltip from "./components/commons/Tooltip.vue";
</script>

<template>
  <header>
    <RouterView name="navbar" />
  </header>
  <main class="container-xl">
    <div class="router-view-wrapper">
      <RouterView />
    </div>
  </main>
  <div><Toast id="toast-global" /></div>
</template>
<style>
  .router-view-wrapper {
    position: relative;
  }
</style>
