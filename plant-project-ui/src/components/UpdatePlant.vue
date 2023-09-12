<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate() };
    },
    data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        sizeFile: true,
        plant: {
          commonName: null,
          latinName: null,
          description: null,
          image: null,
          hydrationId: 0,
          sunlightId: 0,
        },
        inputs: {
          description: null,
          file: null,
        },
      };
    },
    validations() {
      return {
        inputs: {
          description: { required, maxLength: maxLength(1000) },
          file: {
            maxValue: (file) => {
              return file === null || file.size < 512000;
            },
          },
        },
      };
    },
    methods: {
      async getPlant() {
        const response = await this.$axios.get("/plants/{id}");
        const data = response.data;
        this.plant.commonName = data.commonName;
        this.plant.latinName = data.latinName;
        this.plant.image = data.image;
        this.plant.hydrationId = data.hydrationId;
        this.plant.sunlightId = data.sunlightId;

        this.inputs.description = data.description;
      },
      async updatePlant() {
        const valid = await this.v$.$validate();
        if (valid) {
          const formData = new FormData();
          if (this.inputs.file != null) {
            formData.append("image", this.inputs.file);
          }

          formData.append("description", this.inputs.description);

          console.log("valid");
          console.log("this.inputs.file", this.inputs.file);
        }
      },
      async handleFileUpload(event) {
        if (event.target.files[0] != null) {
          this.inputs.file = event.target.files[0];
        }
      },
    },
    async mounted() {
      this.getPlant(false);
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Update plant</h1>
    <div class="col-md-4 mb-5">
      <img :src="fileSystem + plant.image" class="rounded img-fluid" />
    </div>
    <div class="col-md-4">
      <h2 class="plant_name">{{ plant.commonName }} - {{ plant.latinName }}</h2>
      <p>Hydration level : {{ plant.hydrationId.name }}</p>
      <p>Sunlight level : {{ plant.sunlightId.name }}</p>
    </div>
    <form class="row g-3" novalidate @submit.prevent="updatePlant">
      <div class="col-md-4">
        <!-- <label for="image" class="form-label required" maxlength="100"
          >Image</label
        > -->
        <input
          :class="{ 'is-invalid': v$.inputs.file.$error }"
          name="file"
          type="file"
          class="form-control"
          id="file"
          required
          accept="image/png,image/gif,image/jpeg,image/jpg"
          @change="handleFileUpload"
          @keyup.esc=""
        />
        <div class="form-text text-danger" v-if="v$.inputs.file.$error">
          Image size must be less than 500ko
        </div>
        <div v-else id="image-helptext" class="fw-light">Plant's image</div>
      </div>
      <div class="col-12">
        <label for="description" class="form-label required">Description</label>
        <textarea
          name="description"
          class="form-control"
          id="description"
          style="height: 100px"
          minlength="1"
          maxlength="1000"
          required
          rows="10"
          >{{ inputs.description }}</textarea
        >
        <div
          class="form-text"
          :class="{ 'text-danger': v$.inputs.description.$error }"
        >
          Text with a maximum of 1000 chars.
        </div>
        <div id="description-helptext" class="fw-light">
          Plant's description.
        </div>
      </div>
      <div class="d-grid d-md-flex justify-content-md-end">
        <button type="submit" class="btn">Save</button>
      </div>
    </form>
    <div
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
    </div>
  </div>
</template>
<style>
  .btn,
  button {
    color: white;
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .btn:hover,
  button:hover {
    color: white;
    border-color: white;
    background-color: #355e3b;
    cursor: pointer;
  }
  #input-name:focus,
  #latin:focus,
  #water:focus,
  #sun:focus,
  #image:focus,
  #description:focus {
    border-color: #355e3b;
    box-shadow: 0 0 0 0.2rem #355e3b;
  }
</style>
