<script>
  import { RouterLink } from "vue-router";
  import { Toast } from "bootstrap";
  export default {
    data() {
      return {
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
      // showToast() {
      //   const toastDiv = document.querySelector(".toast");
      //   const toast = new Toast(toastDiv);
      //   toast.show();
      // },
      async handleDelete(id) {
        const resp = await this.$axios.delete(`plants/${id}`);
        if ((resp.status = 204)) {
          await this.initPlants();
          //this.showToast();
          this.$toast.success("toast-global", "Plant deleted with  success.");
        } else {
          console.log(resp);
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
    <div class="d-grid d-md-flex justify-content-md-end">
      <a href="/admin/plants/create"
        ><button role="button" class="btn btn-create m-4">
          <i class="bi bi-plus-circle"></i> Create a plant
        </button></a
      >
    </div>
    <div class="container-xl">
      <div class="row row-title fw-bold py-3">
        <div class="col">Common name</div>
        <div class="col">Latin name</div>
        <div class="col">Description</div>
        <div class="col">Image</div>
        <div class="col">Hydration level</div>
        <div class="col">Sunlight level</div>
        <div class="col">Action</div>
      </div>
    </div>

    <div class="container-xl">
      <div
        v-for="plant in plants"
        :key="plant.id"
        class="item row py-2 border border-light border-top-0 rounded-bottom"
      >
        <div class="col text-truncate">{{ plant.commonName }}</div>
        <div class="col text-truncate">{{ plant.latinName }}</div>
        <div class="col text-truncate">{{ plant.description }}</div>
        <div class="col text-truncate">{{ plant.image }}</div>
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
            class="btn btn-delete"
          >
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </div>
      <!-- success toast -->
      <!-- <div
        class="toast align-items-center text-white bg-success border-0"
        role="alert"
        aria-live="assertive"
        aria-atomic="true"
      >
        <div class="d-flex">
          <div class="toast-body">Plant was deleted successfully.</div>
          <button
            type="button"
            class="btn-close btn-close-white me-2 m-auto"
            data-bs-dismiss="toast"
            aria-label="Close"
          ></button>
        </div>
      </div> -->
      <!-- success toast -->

      <!-- toast sure? -->
      <!-- <div
        aria-live="polite"
        aria-atomic="true"
        class="d-flex justify-content-center align-items-center w-100"
      >
        <div
          class="toast top-50 start-50 translate-middle"
          role="alert"
          aria-live="assertive"
          aria-atomic="true"
          data-bs-autohide="false"
        >
          <div class="toast-body">
            Delete this item ?
            <div class="mt-2 pt-2 border-top">
              <button
                class="btn btn-primary btn-sm"
                @click="handleDelete(plant.id)"
              >
                Delete
              </button>
              <button
                type="button"
                class="btn btn-secondary btn-sm"
                data-bs-dismiss="toast"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div> -->
      <!-- toast -->
    </div>
  </div>
</template>
<style>
  .btn-create {
    color: white;
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .btn-create:hover {
    color: white;
    border-color: white;
    background-color: #355e3b;
    cursor: pointer;
  }
  .row-title {
    background-color: #f9f5f1;
  }
  .bi-pencil,
  .bi-pencil:hover,
  .bi-pencil:focus {
    color: green;
  }
  .btn-delete,
  .btn-delete:hover,
  .btn-delete:focus {
    color: red;
  }
  .item:hover {
    background-color: #f8f9fa;
  }
</style>
