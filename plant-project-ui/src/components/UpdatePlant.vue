<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  import { useRoute } from "vue-router";
  export default {
    setup() {
      return { route: useRoute(), v$: useVuelidate() };
    },
    data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        sizeFile: true,
        id: this.route.params.id,
        hydrationLevels: [],
        sunlightLevels: [],
        inputs: {
          commonName: null,
          latinName: null,
          description: null,
          image: null,
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
          hydrationId: { required, minValue: minValue(1) },
          sunlightId: { required, minValue: minValue(1) },
          file: {
            maxValue: (file) => {
              return file === undefined || file.size < 512000;
            },
          },
        },
      };
    },
    methods: {
      async updatePlant() {
        const valid = await this.v$.$validate();
        if (valid) {
          const formData = new FormData();
          if (this.inputs.file != null) {
            formData.append("image", this.inputs.file);
          }
          formData.append("commonName", this.inputs.commonName);
          formData.append("latinName", this.inputs.latinName);
          formData.append("description", this.inputs.description);
          formData.append("hydrationId", this.inputs.hydrationId);
          formData.append("sunlightId", this.inputs.sunlightId);
          const resp = await this.$http.patch(`/plants/${this.id}`, formData);
          if (resp.status == 204) {
            console.log("valid");
            console.log("this.inputs.file", this.inputs.file);
          } else {
            console.log("error");
            console.log(resp.status);
          }
        }
      },
      async handleFileUpload(event) {
        if (event.target.files[0] != null) {
          this.inputs.file = event.target.files[0];
        }
      },
      async getPlantForUpdate() {
        const response = await this.$http.get(`/plants/${this.id}/for-update`);
        this.inputs = response.body;
        console.log(response.body);
        console.log(this.inputs.hydration.name);
        // console.log(this.inputs.hydrationId, "heloo"); undefined
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
      this.getPlantForUpdate();
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Update plant</h1>
    <div class="col-md-2">
      <img :src="fileSystem + inputs.image" class="rounded img-fluid" />
    </div>
    <div class="col-md-8">
      <form novalidate @submit.prevent="updatePlant">
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
          <span
            class="form-text text-danger"
            v-if="v$.inputs.commonName.$error"
          >
            Maximum of 100 chars
          </span>
          <span id="input-name-helptext" class="fw-light" v-else>
            Plant's common name
          </span>
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
          <span class="form-text text-danger" v-if="v$.inputs.latinName.$error">
            Maximum of 200 chars
          </span>
          <span id="latin-helptext" class="fw-light" v-else>
            Plant's latin name
          </span>
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
            <option selected disabled value="0">
              Select an hydratation level
            </option>
            <option
              v-for="hydrationLevel in hydrationLevels"
              :key="hydrationLevel.id"
              :value="hydrationLevel.id"
            >
              {{ hydrationLevel.name }}
            </option>
          </select>
          <span
            class="form-text text-danger"
            v-if="v$.inputs.hydrationId.$error"
          >
            The value is required
          </span>
          <span
            id="hydratation-helptext"
            class="fw-light"
            v-else="v$.inputs.hydrationId.$error"
          >
            Plant's hydratation level
          </span>
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
            <option selected disabled value="0">Select a sunlight level</option>
            <option
              v-for="sunlightLevel in sunlightLevels"
              :key="sunlightLevel.id"
              :value="sunlightLevel.id"
            >
              {{ sunlightLevel.name }}
            </option>
          </select>
          <span
            class="form-text text-danger"
            v-if="v$.inputs.sunlightId.$error"
          >
            The value is required
          </span>
          <span
            id="sunlight-helptext"
            class="fw-light"
            v-else="v$.inputs.sunlightId.$error"
          >
            Plant's sunlight level
          </span>
        </div>
        <div class="col-md-4">
          <!-- <h2 class="plant_name">
          {{ inputs.commonName }} - {{ inputs.latinName }}
        </h2>
        <p>Hydration level : {{ inputs.hydration.name }}</p>
        <p>Sunlight level : {{ inputs.sunlight.name }}</p> -->
        </div>

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
        <div class="col-8">
          <label for="description" class="form-label required"
            >Description</label
          >
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
