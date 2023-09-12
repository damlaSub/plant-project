<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate() };
    },
    data() {
      return {
        // FileSystem: import.meta.env.VITE_IMG_BASE_URL,
        // sizeFile: true,
        hydratationLevels: [],
        sunlightLevels: [],
        plant: {
          commonName: null,
          latinName: null,
          description: null,
          file: undefined,
          hydrationId: 0,
          sunlightId: 0,
        },
      };
    },
    validations() {
      return {
        inputs: {
          commonName: { required, maxLength: maxLength(100) },
          latinName: { required, maxLength: maxLength(200) },
          description: { required, maxLength: maxLength(1000) },
          hydrationId: { minValue: minValue(1) },
          sunlightId: { minValue: minValue(1) },
          file: {
            required,
            maxValue: (file) => {
              return file.size < 512000;
            },
          },
        },
      };
    },
    methods: {
      async submit() {
        const resp = await this.$http.post("/plants", this.inputs);
        if (resp.status === 204) {
          Object.assign(this.inputs, this.$options.data().inputs);
          this.$v.$reset();
        } else {
          console.error(resp);
        }
      },
      async initSunlightLevels() {
        const resp = await this.$http.get("/sunlights");
        this.sunlightLevels = resp.body;
      },
      async initHydrationLevels() {
        const resp = await this.$http.get("/hydrations");
        this.hydrationLevels = resp.body;
      },
    },
    beforeMount() {
      this.initSunlightLevels();
      this.initHydrationLevels();
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Create a plant</h1>
    <form class="row g-3" novalidate @submit.prevent="submit">
      <div class="col-md-4">
        <label for="input-name" class="form-label required" maxlength="100"
          >Common name</label
        >
        <input
          v-model.trim="inputs.commonName"
          name="input-name"
          type="text"
          class="form-control"
          id="input-name"
          required
        />
        <div class="form-text text-danger">Maximum of 100 chars</div>
        <div id="input-name-helptext" class="fw-light">Plant's common name</div>
      </div>
      <div class="col-md-8">
        <label for="latin" class="form-label required" maxlength="200"
          >Latin name</label
        >
        <input
          v-model.trim="inputs.latinName"
          name="latin"
          type="text"
          class="form-control"
          id="latin"
          required
        />
        <div class="form-text text-danger">Maximum of 200 chars</div>
        <div id="latin-helptext" class="fw-light">Plant's latin name</div>
      </div>
      <div class="col-md-4">
        <label for="hydrationId" class="form-label required"
          >Hydratation level</label
        >
        <select
          v-model.number="inputs.hydrationId"
          name="hydrationId"
          class="form-select"
          id="hydrationId"
        >
          <option selected disabled value="0">Select hydratation</option>
          <option v-for="hydrationLevel in hydrationLevels">
            {{ hydrationLevel.name }}
          </option>
        </select>
        <div id="hydratation-helptext" class="fw-light">
          Plant's hydratation level
        </div>
      </div>
      <div class="col-md-4">
        <label for="sunlightId" class="form-label required"
          >Sunlight level</label
        >
        <select
          v-model.number="inputs.sunlightId"
          name="sunlightId"
          class="form-select"
          id="sunlightId"
        >
          <option selected disabled value="0">Select sunlight</option>
          <option v-for="sunlightLevel in sunlightLevels">
            {{ sunlightLevel.name }}
          </option>
        </select>
        <div id="sunlight-helptext" class="fw-light">
          Plant's sunlight level
        </div>
      </div>
      <div class="col-md-4">
        <label for="image" class="form-label required" maxlength="100"
          >Image</label
        >
        <input
          name="image"
          type="file"
          class="form-control"
          id="image"
          required
          accept="image/png,image/gif,image/jpeg, image/jpg"
          @change="handleFileUpload"
          @keyup.esc=""
        />
        <div class="form-text text-danger">
          Image size must be less than 500ko
        </div>
        <div id="image-helptext" class="fw-light">Plant's image</div>
      </div>
      <div class="col-12">
        <label for="description" class="form-label required">Description</label>
        <textarea
          v-model.trim="inputs.description"
          name="description"
          class="form-control"
          id="description"
          style="height: 100px"
          minlength="1"
          maxlength="1000"
          required
        ></textarea>
        <div class="form-text text-danger">
          Text with a maximum of 1000 chars
        </div>
        <div id="description-helptext" class="fw-light">
          Plant's description
        </div>
      </div>
      <div class="d-grid d-md-flex justify-content-md-end">
        <button type="submit" class="btn">Save</button>
      </div>
    </form>
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
