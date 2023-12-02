<script>
  import { RouterLink } from "vue-router";
  export default {
    data() {
      return {
        // isAuthenticated:
        //   localStorage.getItem(isAuthenticated) != undefined &&
        //   localStorage.getItem(isAuthenticated) == true,
      };
    },
    methods: {
      signout() {
        localStorage.clear();
        this.$router.push("/signin");
      },
    },
  };
</script>

<template>
  <div>
    <header class="fixed-top">
      <nav class="navbar navbar-expand-md container-xl">
        <div class="container">
          <a class="navbar-brand" href="/">
            <img
              src="../../assets/logo.png"
              alt="Bootstrap"
              width="60"
              height="48"
            />
          </a>
        </div>
        <button
          class="navbar-toggler me-2"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item fs-5">
              <RouterLink :to="{ name: 'signin' }" class="navbar-brand"
                >Sign in</RouterLink
              >
            </li>
            <li class="nav-item fs-5">
              <RouterLink :to="{ name: 'signup' }" class="navbar-brand"
                >Sign up</RouterLink
              >
            </li>
            <li class="nav-item fs-5">
              <a @click="signout" class="signout">Sign out</a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  </div>
</template>

<style>
  .navbar {
    background-color: white;
  }
  navbar-brand {
    font-family: Chromatica, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  a.signout,
  a.signout:hover,
  a.signout:focus {
    text-decoration: none;
    color: black;
    cursor: pointer;
    white-space: nowrap;
    padding: 5px 0px;
    margin: 0px 16px 0px 0px;
  }
</style>
