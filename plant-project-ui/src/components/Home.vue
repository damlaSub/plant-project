<script>
  export default {
    data() {
      return {
        baseUrl: import.meta.env.VITE_IMG_BASE_URL,
        plants: [],
      };
    },
    methods: {
      async getPlants() {
        const resp = await this.$http.get("/plants");
        this.plants = resp.body;
      },
    },
    beforeMount() {
      this.getPlants();
    },
  };
</script>
<template>
  <h1>Plants</h1>
</template>
