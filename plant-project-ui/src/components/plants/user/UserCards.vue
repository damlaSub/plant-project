<script>
  export default {
    data() {
      return {
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        myPlants: [],
        plantId: null,
      };
    },
    methods: {
      async initMyPlants() {
        const resp = await this.$axios.get("/my-plants");
        this.myPlants = resp.body;
      },

      async deletePlant(id) {
        await this.$axios.delete(`my-plants/${id}/delete`).then((response) => {
          this.$toast.success("toast-global", this.$t("success.delete"));
          this.initMyPlants();
        });
      },
    },
    beforeMount() {
      this.initMyPlants();
    },
  };
</script>
<template>
  <div class="row row-cols-1 row-cols-md-4 g-3 p-5">
    <div class="col" v-for="plant in myPlants" :key="plant.id">
      <div class="card h-100">
        <div class="p-3">
          <RouterLink :to="{ name: 'user-plant-detail', params: { id: plant.id } }" class="ms-auto">
          <img
            :src="baseUrl + plant.image"
            class="card-img-top plant-img"
            :alt="plant.name"
          />
        </RouterLink>  
        </div>
        <div class="card-body">
          <div>
            <h5 class="card-title">{{ plant.commonName }}</h5>
          </div>

          <div class="d-flex">
            <ul>
              <span v-for="n in plant.sunlightId">
                <img src="/images/sun.svg" />
              </span>
            </ul>

            <ul>
              <span v-for="n in plant.hydrationId">
                <img src="/images/water.svg" />
              </span>
            </ul>
          </div>
          <div class="d-grid d-md-flex justify-content-md-end">
            <button
              @click="deletePlant(plant.id)"
              type="button"
              class="btn btn-delete"
            >
            <i class="bi bi-x-lg"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
  button.btn.btn-delete, button.btn.btn-delete:hover, button.btn.btn-delete:active {
    color: #f9f5f1;
    background-color: #355e3b;
    border-style: none;
  }
 
  #basic-addon1 {
    background-color: #355e3b;
  }

  .search:focus {
    box-shadow: 0 0 0 0.2rem #355e3b;
  }
  .plant-img {
    height: 100%;
    width: 100%;
  }
</style>
