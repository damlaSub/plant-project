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
      },
    },
    beforeMount() {
      this.initPlants();
    },
  };
</script>
<template>
  <h1>All plants</h1>
  <div class="row row-cols-md-4 g-3 p-5">
    <div class="col" v-for="plant in plants">
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
