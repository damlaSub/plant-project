<script>
  import { RouterLink } from "vue-router";
  export default {
  data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
      }
    }
  }
</script>
<template>
  <header class="header">
    <nav
      class="navbar navbar-expand-md bg-body-tertiary"
      style="background-color: #355e3b"
    >
      <div class="container-fluid">
        <a class="navbar-brand" href="/"
          ><img  :src="fileSystem + 'plant.png'"  alt="Bootstrap"
            width="60"
            height="48"/></a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarScroll"
          aria-controls="navbarScroll"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
          <ul
            class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
            style="--bs-scroll-height: 100px"
          >
            <li class="nav-item">
              <RouterLink
                :to="{ name: 'home' }"
                class="navbar-brand"
              ></RouterLink>
            </li>
          </ul>
          <li class="nav-item dropdown">
            <ul class="navbar-nav">
              <li class="nav-item fs-5">
                <RouterLink :to="{ name: 'signin' }" class="navbar-brand">{{
                  $t("auth.signIn")
                }}</RouterLink>
              </li>
              <li class="nav-item fs-5">
                <RouterLink :to="{ name: 'signup' }" class="navbar-brand">{{
                  $t("auth.signUp")
                }}</RouterLink>
              </li>
            </ul>
          </li>
        </div>
      </div>
      <div class="locale-changer"></div>
    </nav>
  </header>
</template>
<style>
  li {
    list-style-type: none;
  }
  .navbar-brand {
    font-size: 16px;
  }
  .navbar-brand:hover {
    text-decoration: underline;
  }
</style>
