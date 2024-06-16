<script>
  import { RouterLink } from "vue-router";
  export default {
    data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        toast: null,
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        plants: [],
      };
    },
    methods: {
      async initPlants() {
        const resp = await this.$axios.get("/plants");
        this.plants = resp.body;
      },

      async handleDelete(id) {
        const resp = await this.$axios.delete(`plants/admin/${id}/delete`);
        if ((resp.status = 204)) {
          await this.initPlants();
          this.$toast.success("toast-global", this.$t("success.delete"));
        } else {
          console.log(resp);
          this.$toast.error("toast-global", this.$t("error.try"));
        }
      },
    },
    beforeMount() {
      this.initPlants();
    },
  };
</script>
<template>
  <div class="mb-3 p-5">
    <div class="container-xl">
      <div class="d-grid pb-3">
      <RouterLink :to="{ name: 'create' }" class="admin-create">
        <button role="button" class="btn btn-create custom-button">
          <div> <i class="bi bi-plus-circle"></i> {{ $t("title.createPlant") }}</div>
         
        </button></RouterLink
      >
    </div>
    </div>
    <div class="container-xl">
      <div class="row row-title fw-bold py-3 px-3">
        <div class="col text-center">{{ $t("title.common") }}</div>
        <div class="col text-center d-none d-md-block">{{ $t("title.latin") }}</div>
        <div class="col text-center d-none d-lg-block">
          {{ $t("title.hydration") }}
        </div>
        <div class="col text-center text-end d-none d-lg-block">
          {{ $t("title.sunlight") }}
        </div>
        <div class="col text-end">{{ $t("title.action") }}</div>
      </div>
    </div>

    <div v-for="plant in plants" :key="plant.id" class="container-xl">
      <div class="item row py-2 border border-top-0 rounded-bottom">
        <div class="col">
          <div class="row">
            <div class="col-4">
              <button
                class="btn collapse-btn collapsed"
                type="button"
                data-bs-toggle="collapse"
                :data-bs-target="'#collapse' + plant.id"
                aria-expanded="false"
                :aria-controls="'collapse' + plant.id"
              >
                <i
                  class="bi bi-chevron-up collapse-icon"
                  data-bs-toggle="collapse"
                  :href="'#collapse' + plant.id"
                  role="button"
                  aria-expanded="false"
                  :aria-controls="'collapse' + plant.id"
                ></i>
              </button>
            </div>
            <div class="col text-truncate">
              {{ plant.commonName }}
            </div>
          </div>
        </div>
        <div class="col text-truncate text-center d-none d-md-block">
          {{ plant.latinName }}
        </div>
        <div class="col text-center d-none d-lg-block">
          {{ plant.hydration.name }}
        </div>
        <div class="col text-center d-none d-lg-block">
          {{ plant.sunlight.name }}
        </div>
        <div class="col text-center">
          <RouterLink
            :to="{ name: 'update', params: { id: plant.id } }"
            title="Update plant"
          >
            <i class="bi bi-pencil"></i>
          </RouterLink>
          <button
            data-bs-toggle="tooltip"
            data-bs-placement="top"
            title="Tooltip on top"
            @click="handleDelete(plant.id)"
            type="button"
            class="btn-delete"
          >
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </div>
      <div class="row border border-top-0 rounded-bottom detail-view">
        <div class="collapse row" :id="'collapse' + plant.id">
          <div
            class="clearfix col-4 d-flex justify-content-center align-items-center"
          >
            <img
              :src="fileSystem + plant.image"
              class="rounded-circle img-edit"
            />
          </div>
          <div class="col-8 d-flex justify-content-center align-items-center">
            <p class="col-12 overflow-auto description-text " v-html="plant.description">              
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.btn-create{
  width: 100%;
}
.custom-button {
  color: black !important;
  background-color: #e8d9c9 !important;
  border-color: #e8d9c9 !important;
  text-decoration: none !important;
}

.custom-button:hover,
.custom-button:focus,
.custom-button:active {
  color: black !important;
  background-color: #e8d9c9 !important;
  border-color: #e8d9c9 !important;
  text-decoration: none !important;
}

@media (min-width: 768px) {
  .btn-create, .btn-create:hover, .btn-create:active {
    width: auto; 
    text-decoration: none;
  }

  .admin-create {
    display: flex;
    justify-content: end; 
    text-decoration: none;
  }
}

@media (max-width: 767.98px) {
  .admin-create, .btn-create:hover, .btn-create:active {
    display: flex;
    justify-content: center;
    text-decoration: none;
  }   
} 
  .row-title {
    background-color: #e8d9c9;
  }
  .item {
    border-color: #e8d9c9;
  }
  .item:hover,
  .item:hover .btn-delete,
  .item:hover ~ .detail-view {
    background-color: #f0e6db;
  }

  .bi-pencil {
    color: green;
  }
  .btn-delete {
    color: red;
    background-color: #f9f5f1;
    border-style: none;
  }
  .img-edit {
    width: 70%;
    height: auto;
    max-width: 100%;
    border-radius: 50%;
  }
  .collapsing {
    transition-duration: 0.1s;
  }
  button.btn.btn-collapse {
    position: relative;
  }
  button.btn.collapse-btn,
  button.btn.collapse-btn.collapsed {
    border-style: none;
  }
  button.btn.collapse-btn .collapse-icon {
    border-style: none;
    position: absolute;
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
  }
  button.btn.collapse-btn.collapsed .collapse-icon {
    transform: rotate(
      180deg
    ); 
  }
.description-text {
  font-size: 1rem; 
}

@media (max-width: 768px) {
  .description-text {
    font-size: 0.875rem; 
  }
}

@media (max-width: 576px) {
  .description-text {
    font-size: 0.75rem; 
  }
}
</style>
