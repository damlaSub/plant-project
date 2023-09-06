<script>
  export default {
    data() {
      return {
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        plants: [],
      };
    },
    methods: {
      async initPlants() {
        const resp = await this.$http.get("/plants");
        this.plants = resp.body;
        console.log(this.plants);
      },
      handleDelete() {
        // remove() ...
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
      <a href="/admin/create"
        ><button role="button" class="btn btn-create mb-4">
          <i class="bi bi-plus-circle"></i> Create a plant
        </button></a
      >
    </div>
    <div class="container-xl">
      <div class="row row-title fw-bold py-3">
        <div class="col">Name</div>
        <div class="col">Latin name</div>
        <div class="col">Description</div>
        <div class="col">Image</div>
        <div class="col">Water level</div>
        <div class="col">Sun level</div>
        <div class="col">Action</div>
      </div>
    </div>

    <div class="container-xl">
      <div
        v-for="plant in plants"
        class="row py-2 border border-light border-top-0 rounded-bottom"
      >
        <div class="col text-truncate">{{ plant.name }}</div>
        <div class="col text-truncate">{{ plant.latinName }}</div>
        <div class="col text-truncate">{{ plant.description }}</div>
        <div class="col">{{ plant.image }}</div>
        <div class="col">{{ plant.water.name }}</div>
        <div class="col">{{ plant.sun.name }}</div>
        <div class="col">
          <a class="icon-update" href="/admin/update">
            <i class="bi bi-pencil"></i>
          </a>
          <button @click="handleDelete()" type="button" class="btn btn-delete">
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </div>
    </div>
    <!-- <div
      class="toast top-50 start-50 translate-middle"
      role="alert"
      aria-live="assertive"
      aria-atomic="true"
    >
      <div class="toast-body">
        Delete this item ?
        <div class="mt-2 pt-2 border-top">
          <button type="button" class="btn btn-primary btn-sm">Delete</button>
          <button
            type="button"
            class="btn btn-secondary btn-sm"
            data-bs-dismiss="toast"
          >
            Cancel
          </button>
        </div>
      </div>
    </div> -->
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
  .icon-update,
  .icon-update:hover,
  .icon-update:focus {
    color: green;
  }
  .btn-delete,
  .btn-delete:hover,
  .btn-delete:focus {
    color: red;
  }
</style>
