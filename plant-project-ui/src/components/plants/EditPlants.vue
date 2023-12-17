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
          this.$toast.error("toast-global", this.$t("error.err"));
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
    <div class="d-grid d-md-flex justify-content-md-end mt-5">
      <a class="create-a" href="/admin/plants/create"
        ><button role="button" class="btn btn-create m-4">
          <i class="bi bi-plus-circle"></i> {{ $t("title.createPlant") }}
        </button></a
      >
    </div>
    <div class="container-xl">
      <div class="row row-title fw-bold py-3 px-3">
        <div class="col">{{ $t("title.common") }}</div>
        <div class="col">{{ $t("title.latin") }}</div>
        <div class="col">{{ $t("title.hydration") }}</div>
        <div class="col">{{ $t("title.sunlight") }}</div>
        <div class="col">{{ $t("title.action") }}</div>
      </div>
    </div>

    <div v-for="plant in plants" :key="plant.id" class="container-xl">
      <div class="item row py-2 border border-top-0 rounded-bottom">
        <div class="col">
          <div class="row">
            <div class="col-4">
              <button
                class="btn collapse-icon"
                type="button"
                data-bs-toggle="collapse"
                :data-bs-target="'#collapse' + plant.id"
                aria-expanded="false"
                :aria-controls="'collapse' + plant.id"
              >
                <i
                  class="bi bi-chevron-down"
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
        <div class="col text-truncate">{{ plant.latinName }}</div>
        <div class="col">{{ plant.hydration.name }}</div>
        <div class="col">{{ plant.sunlight.name }}</div>
        <div class="col">
          <RouterLink
            :to="{ name: 'update', params: { id: plant.id } }"
            title="Update plant"
          >
            <i class="bi bi-pencil"></i>
          </RouterLink>
          <button
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
            <p class="col-12 overflow-auto">
              {{ plant.description }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
  .btn-create,
  .create-a {
    color: #355e3b;
    background-color: #f9f5f1;
    border-color: black;
    border-radius: 12px;
    padding: 6px 14px;
  }
  .btn-create:hover,
  .create-a:hover,
  .create-a:focus,
  .create-a:active,
  .btn-create:focus,
  .btn-create:active {
    color: #355e3b;
    background-color: #f9f5f1;
    border-color: #355e3b;
    border-radius: 12px;
    padding: 6px 14px;
    cursor: pointer;
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
    height: 70%;
    border-radius: 50%;
  }
  .collapse-icon {
    border-style: none;
  }
</style>
