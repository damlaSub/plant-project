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
        const resp = await this.$axios.get("/plants");
        this.plants = resp.body;
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
                <img
                    :src="baseUrl + plant.image"
                    class="card-img-top plant-img"
                    :alt="plant.name"
                />
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
                <div class="d-flex align-items-center">
                    <p class="card-text text-truncate">{{ plant.description }}</p>
                </div>
                <div class="d-grid d-md-flex justify-content-md-end"></div>
            </div>
        </div>
    </div>
</div>
</template>
<style>
  .btn-add {
    color: #355e3b;
    background-color: #f9f5f1;
    border-color: black;
    border-radius: 6%;
    padding: 1% 4%;
  }
  .btn-down {
    color: #355e3b;
    background-color: #f9f5f1;
    border-color: black;
    border-radius: 12px;
    padding: 6px 14px;
  }
  .btn-add:hover,
  .btn-down:hover {
    cursor: pointer;
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
