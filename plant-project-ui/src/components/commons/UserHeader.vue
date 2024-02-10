<script>
  import { RouterLink } from "vue-router";
  import { ref, onMounted } from "vue";
  export default {
    setup() {
      const userName = ref("");
      onMounted(() => {
        try {
          userName.value = localStorage.getItem("userName") || "";
        } catch (error) {
          console.error("Error retrieving userName from localStorage:", error);
        }
      });
      return {
        userName,
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
  <header class="fixed-top px-5">
    <nav
      class="navbar navbar-expand-lg bg-body-tertiary"
      style="background-color: #e8d9c9"
    >
      <div class="container-fluid">
        <RouterLink :to="{ name: 'user-home' }" class="navbar-brand">
          <img
            src="../../assets/plant.png"
            alt="Bootstrap"
            width="60"
            height="48"
        /></RouterLink>
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
                :to="{ name: 'user-home' }"
                class="navbar-brand"
              ></RouterLink>
            </li>
          </ul>
          <li class="nav-item dropdown">
            <ul class="navbar-nav">
              <ul class="px-3">
                <li class="nav-item fs-5">
                  <button
                    class="user-icon d-md-block d-none"
                    type="button"
                    data-bs-toggle="offcanvas"
                    data-bs-target="#offcanvasRight"
                    aria-controls="offcanvasRight"
                  >
                    <i class="bi bi-person-circle"></i>
                  </button>
                </li>
              </ul>
              <ul class="px-3">
                <li class="d-md-block d-none">
                  <span>{{ userName }} </span>
                </li>
                <li class="d-md-none d-block">
                  <i class="bi bi-person-circle canva"></i>
                  <span>{{ userName }} </span>
                </li>
                <li class="d-md-none d-block">
                  <i class="bi bi-house-door canva"></i>
                  <RouterLink
                    :to="{ name: 'user-home' }"
                    class="user-home-link"
                    >{{ $t("title.home") }}</RouterLink
                  >
                </li>
                <li class="d-md-none d-block">
                  <i class="bi bi-flower1 canva"></i>
                  <RouterLink :to="{ name: 'dashboard' }" class="my-plant">{{
                    $t("title.myPlants")
                  }}</RouterLink>
                </li>
                <li>
                  <i class="bi bi-box-arrow-right"></i>
                  <a @click="signout" class="signout"
                    >{{ $t("auth.signOut") }}
                  </a>
                </li>
              </ul>
            </ul>
          </li>
        </div>
      </div>
    </nav>
  </header>
</template>

<style>
  li {
    list-style-type: none;
  }
  .user-icon {
    background-color: #e8d9c9;
    border-style: none;
  }
</style>
