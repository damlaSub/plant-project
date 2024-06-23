<script>
  import { useRoute } from "vue-router";
  export default {
    setup() {
      return {
            route:useRoute()
        }
    },
    data() {
      return {
        id: this.route.params.id,
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        plant: {},
      };
    },

    methods: {
        async initPlant() {
            const resp = await this.$axios.get(`/plants/${this.id}`);
            this.plant = resp.body;
            console.log(resp.body)
       },

    },
    beforeMount() {
        this.initPlant();
      },
  }
  </script>
<template>
 <div class="mt-1 p-5">
    <h1 class="fs-4 card-title fw-bold mb-2">{{ plant.commonName }}</h1>
    <p class="fw-light">{{ plant.latinName }}</p>
    <div class="card mb-3"  v-if="plant.commonName">
      <div class="row g-0">
        <div class="col-md-4">
          <img
            :src="fileSystem + plant.image"
            class="img-fluid plant-detail"
          />
        </div>
        <div class="col-md-8">
          <div class="card-body pt-5">
            <div class="fw-light card-text" v-html="plant.description"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



