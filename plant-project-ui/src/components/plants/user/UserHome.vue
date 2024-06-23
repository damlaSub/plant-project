<script>
  import UserCanvas from "./UserCanvas.vue";

  export default {
    data() {
      return {
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        plants: [],
        plantStatus: {},
        input: "",
        plantId: null,
      };
    },
    methods: {
      async initPlants() {
        const resp = await this.$axios.get("/plants");
        this.plants = resp.body;
      },

      async initPlantStatus() {
        const resp = await this.$axios.get("/my-plants/status");
        this.plantStatus = resp.body;
      },

      filteredPlantList() {
        return this.plants.filter(
          (plant) =>
            plant.commonName.toLowerCase().includes(this.input.toLowerCase()) ||
            plant.latinName
              .toLowerCase()
              .includes(this.input.toLocaleLowerCase())
        );
      },
      async addPlant(plant) {
        const plantData = {
          plantId: plant.id,
        };
        await this.$axios.post("my-plants/add", plantData).then((response) => {
          if (response.status === 204) {
            this.initPlantStatus();
            this.$toast.success("toast-global", this.$t("success.add"));
          } else {
            this.$toast.error("toast-global", this.$t("error.try"));
          }
        });
      },
    },
    beforeMount() {
      this.initPlants();
      this.initPlantStatus();
    },
    mounted() {
      this.filteredPlantList();
    },
    components: { UserCanvas },
  };
</script>
<template>
  <UserCanvas />
  <div class="input-group mt-5 mb-3 p-5 mt-5">
    <span class="input-group-text" id="basic-addon1"
      ><i class="bi bi-search"></i
    ></span>

    <input
      type="text"
      v-model.trim="input"
      ref="input"
      class="form-control search"
      placeholder="Search plants..."
      aria-label="Search plants"
      aria-describedby="basic-addon1"
    />
  </div>
  
  <div
    class="p-5 item error text-danger"
    v-if="input && !filteredPlantList().length"
  >
    <p>{{ $t("title.noResult") }}</p>
  </div>
  <div class="row row-cols-1 row-cols-md-4 g-3 p-5">
    <div class="col" v-for="plant in filteredPlantList()" :key="plant.id">
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
              <span v-for="n in plant.sunlight.logicalOrder">
                <img src="/images/sun.svg" />
              </span>
            </ul>

            <ul>
              <span v-for="n in plant.hydration.logicalOrder">
                <img src="/images/water.svg" />
              </span>
            </ul>
          </div>
          <div class="d-grid d-md-flex justify-content-md-end">
            <button
              @click="addPlant(plant)"
              :disabled="plantStatus[plant.id] === true"
              type="button"
              class="btn btn-add"
            >
            <i class="bi bi-plus-lg"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
  button.btn.btn-add,  button.btn.btn-add:focus,  button.btn.btn-add:active {
    color: #f9f5f1;;
    background-color: #355e3b;
    border-color: #355e3b;
    box-shadow: 0 0 0 0.01rem black;
  }
  button.btn.btn-add:disabled{
    color: #f9f5f1;;
    background-color: #496e4e;
    border-color:#496e4e;
    box-shadow: 0 0 0 0.01rem black;
  }
  #basic-addon1 {
    background-color: #355e3b;
  }

  .search:focus {
    box-shadow: 0 0 0 0.1rem #355e3b;
  }
  .plant-img {
    height: 100%;
    width: 100%;
  }
</style>
