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
  };
</script>
<template>
  <div
    class="offcanvas offcanvas-end"
    tabindex="-1"
    id="offcanvasRight"
    aria-labelledby="offcanvasRightLabel"
  >
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasRightLabel">
        {{ $t("title.dashboard") }}
      </h5>
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="offcanvas"
        aria-label="Close"
      ></button>
    </div>
    <div class="offcanvas-body">
      <ul>
        <li>
          <i class="bi bi-person-circle canva"></i> <span>{{ userName }} </span>
        </li>
        <li>
          <i class="bi bi-house-door canva"></i>
          <RouterLink :to="{ name: 'user-home' }" class="user-home-link">{{
            $t("title.home")
          }}</RouterLink>
        </li>
        <li>
          <i class="bi bi-heart canva"></i>
          <RouterLink :to="{ name: 'dashboard' }" class="my-plant">{{
            $t("title.myPlants")
          }}</RouterLink>
        </li>
        <li>
          <i class="bi bi-box-arrow-right canva"></i
          ><a @click="signout" class="signout">{{ $t("auth.signOut") }} </a>
        </li>
      </ul>
    </div>
  </div>
</template>
<style>
  .offcanvas {
    background-color: #f9f5f1;
  }
  .my-plant,
  .my-plant:hover,
  .my-plant:active,
  .my-plant:focus,
  .user-home-link,
  .user-home-link:hover,
  .user-home-link:active,
  .user-home-link:focus {
    color: black;
  }
  .my-plant,
  .user-home-link {
    text-decoration: none;
  }
  .canva {
    padding-right: 2px;
  }
</style>
