<script>
  export default {
    data() {
      return {
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        plants: [],
        input: "",
      };
    },
    methods: {
      async initPlants() {
        const resp = await this.$http.get("/plants");
        this.plants = resp.body;
      },
      filteredPlantList() {
        return this.plants.filter((plant) =>
          plant.name.toLowerCase().includes(this.input.toLowerCase())
        );
      },
    },
    beforeMount() {
      this.initPlants();
    },
    mounted() {
      this.filteredPlantList();
    },
  };
</script>
<template>
  <div class="input-group mb-3 p-5">
    <span class="input-group-text" id="basic-addon1"
      ><i class="bi bi-search"></i
    ></span>

    <input
      type="text"
      v-model.trim="input"
      ref="input"
      class="form-control"
      placeholder="Search plants..."
      aria-label="Search plants"
      aria-describedby="basic-addon1"
    />
  </div>
  <div class="row">
    <div class="col" role="group">
      <button
        type="button"
        class="btn btn-outline-secondary"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        Sun <i class="bi bi-chevron-down"></i>
      </button>
      <ul class="dropdown-menu">
        <li><img src="../../public/images/sun.svg" /></li>
        <li>
          <img src="../../public/images/sun.svg" /><img
            src="../../public/images/sun.svg"
          />
        </li>
        <li>
          <img src="../../public/images/sun.svg" /><img
            src="../../public/images/sun.svg"
          /><img src="../../public/images/sun.svg" />
        </li>
      </ul>
    </div>
    <div class="col" role="group">
      <button
        type="button"
        class="btn btn-outline-secondary"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        Water <i class="bi bi-chevron-down"></i>
      </button>
      <ul class="dropdown-menu">
        <li>
          <img src="../../public/images/water.svg" />
        </li>
        <li>
          <img src="../../public/images/water.svg" /><img
            src="../../public/images/water.svg"
          />
        </li>
        <li>
          <img src="../../public/images/water.svg" /><img
            src="../../public/images/water.svg"
          /><img src="../../public/images/water.svg" />
        </li>
      </ul>
    </div>
  </div>
  <div
    class="p-5 item error text-danger"
    v-if="input && !filteredPlantList().length"
  >
    <p>No results found!</p>
  </div>
  <div class="row row-cols-md-4 g-3 p-5">
    <div class="col" v-for="plant in filteredPlantList()" :key="plant">
      <div class="card h-100">
        <div class="p-3">
          <img
            :src="baseUrl + plant.imageUrl"
            class="card-img-top"
            :alt="plant.name"
          />
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ plant.name }}</h5>
          <div class="d-flex">
            <ul>
              <span v-for="n in plant.sun">
                <img src="../../public/images/sun.svg" />
              </span>
            </ul>

            <ul>
              <span v-for="n in plant.water">
                <img src="../../public/images/water.svg" />
              </span>
            </ul>
            <button type="button" class="btn btn-light">+</button>
          </div>
          <div class="d-flex align-items-center">
            <p class="card-text">{{ plant.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
  button {
    background-color: #f4ede7;
  }
</style>
