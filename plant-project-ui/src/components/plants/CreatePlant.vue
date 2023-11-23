<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate() };
    },
    data() {
      return {
        hydrationLevels: [],
        sunlightLevels: [],
        inputs: {
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
          hydrationId: { required, minValue: minValue(1) },
          sunlightId: { required, minValue: minValue(1) },
          file: {
            maxValue: (file) => {
              return file ? file.size <= 512000 : false;
            },
          },
        },
      };
    },
    methods: {
      handleFileUpload(event) {
        this.inputs.file = event.target.files[0];
      },
      async submit(event) {
        const valid = await this.v$.$validate();
        const formData = new FormData();
        Object.keys(this.inputs).forEach((key) => {
          const value = this.inputs[key];
          if (valid) {
            formData.append(key, value);
          }
        });
        const resp = await this.$axios.post("/plants/admin/create", formData);
        if (resp.status === 204) {
          Object.assign(this.inputs, this.$options.data().inputs);
          this.v$.$reset();
          event.target.reset();
          this.$toast.success("toast-global", "Plant created with success.");
          this.$router.push("/admin/plants");
        } else {
          this.$toast.error("toast-global", "An error occured.");
        }
      },
      async initSunlightLevels() {
        const resp = await this.$axios.get("/sunlights");
        this.sunlightLevels = resp.body;
      },
      async initHydrationLevels() {
        const resp = await this.$axios.get("/hydrations");
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
  <div class="mt-5 mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Create a plant</h1>
    <form class="row g-3 needs-validation" novalidate @submit.prevent="submit">
      <div class="col-md-4">
        <label for="input-name" class="form-label required" maxlength="100"
          >Common name</label
        >
        <input
          :class="{ 'is-invalid': v$.inputs.commonName.$error }"
          v-model.trim="inputs.commonName"
          name="input-name"
          type="text"
          class="form-control"
          id="input-name"
          required
        />
        <span class="form-text text-danger" v-if="v$.inputs.commonName.$error">
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
          :class="{ 'is-invalid': v$.inputs.latinName.$error }"
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
          :class="{ 'is-invalid': v$.inputs.hydrationId.$error }"
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
        <span class="form-text text-danger" v-if="v$.inputs.hydrationId.$error">
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
          :class="{ 'is-invalid': v$.inputs.sunlightId.$error }"
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
        <span class="form-text text-danger" v-if="v$.inputs.sunlightId.$error">
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
        <label for="image" class="form-label required" maxlength="100"
          >Image</label
        >
        <input
          :class="{ 'is-invalid': v$.inputs.file.$error }"
          name="image"
          type="file"
          class="form-control"
          id="image"
          required
          accept="image/png,image/gif,image/jpeg"
          @change="handleFileUpload"
          @keyup.esc=""
        />
        <span class="form-text text-danger" v-if="v$.inputs.file.$error">
          Image size must be less than 500ko
        </span>
        <span id="image-helptext" class="fw-light" v-else>Plant's image</span>
      </div>
      <div class="col-12">
        <label for="description" class="form-label required" maxlength="1000"
          >Common name</label
        >
        <textarea
          :class="{ 'is-invalid': v$.inputs.description.$error }"
          v-model.trim="inputs.description"
          name="description"
          type="text"
          class="form-control"
          id="description"
          style="height: 100px"
          rows="10"
          required
          >{{ inputs.description }}</textarea
        >
        <span class="form-text text-danger" v-if="v$.inputs.description.$error">
          Maximum of 1000 chars
        </span>
        <span id="description-helptext" class="fw-light" v-else>
          Plant's description
        </span>
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
