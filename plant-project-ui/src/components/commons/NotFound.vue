<template>
  <main class="h-100">
    <div
      class="container h-100 mt-5 d-flex justify-content-center align-items-center"
    >
      <div>
        <h1>404</h1>
        <h2>Page Not Found</h2>
        <h3>The page does not exist or is deleted</h3>
        <div></div>
        <RouterLink :to="{ name: 'home' }">
          <button class="btn button">
            <i class="bi bi-sign-turn-left"></i> Go Home
          </button></RouterLink
        >
      </div>
    </div>
  </main>
</template>
<style>
  .responsive-image {
    max-width: 70%;
    height: auto;
  }
</style>
