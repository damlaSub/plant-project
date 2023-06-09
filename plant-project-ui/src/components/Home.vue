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
  <div class="row row-cols-1 row-cols-md-4 g-3 mb-3">
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
          <div class="d-flex align-items-center">
            <p class="card-text">{{ plant.description }}</p>
            <p class="card-text">water {{ plant.water }}</p>
            <p class="card-text">sun {{ plant.sun }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
