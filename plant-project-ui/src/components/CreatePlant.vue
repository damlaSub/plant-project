<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate() };
    },
    data() {
      return {
        FileSystem: import.meta.env.VITE_IMG_BASE_URL,
        sizeFile: true,
        waters: [],
        suns: [],
        plant: {
          name: null,
          latinName: null,
          description: null,
          imageUrl: null,
          waterId: 0,
          sunId: 0,
        },
      };
    },
    validations() {
      return {
        inputs: {
          name: { required, maxLength: maxLength(100) },
          latinName: { required, maxLength: maxLength(200) },
          description: { required, maxLength: maxLength(1000) },
          waterId: { minValue: minValue(1) },
          sunId: { minValue: minValue(1) },
          file: {
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
      async initWaters() {
        const resp = await this.$http.get("/waters");
        this.waters = resp.body;
      },
      async initSuns() {
        const resp = await this.$http.get("/suns");
        this.suns = resp.body;
      },
    },
    beforeMount() {
      this.initWaters();
      this.initSuns();
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Create a plant</h1>
    <form class="row g-3" novalidate @submit.prevent="">
      <div class="col-md-4">
        <label for="input-name" class="form-label required" maxlength="100"
          >Name</label
        >
        <input
          v-model.trim="inputs.name"
          name="input-name"
          type="text"
          class="form-control"
          id="input-name"
          required
        />
        <div class="form-text text-danger">Maximum of 100 chars</div>
        <div id="input-name-helptext" class="fw-light">Plant's name</div>
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
        <div id="latin-helptext" class="fw-light">Plant's name in latin</div>
      </div>
      <div class="col-md-4">
        <label for="waterId" class="form-label required">Water level</label>
        <select
          v-model.number="inputs.waterId"
          name="waterId"
          class="form-select"
          id="waterId"
        >
          <option selected disabled value="0">Select water</option>
        </select>
        <div id="water-helptext" class="fw-light">Plant's water level</div>
      </div>
      <div class="col-md-4">
        <label for="sunId" class="form-label required">Sun level</label>
        <select
          v-model.number="inputs.sunId"
          name="sunId"
          class="form-select"
          id="sunId"
        >
          <option selected disabled value="0">Select sun</option>
        </select>
        <div id="sun-helptext" class="fw-light">Plant's sun level</div>
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
          accept="image/png,image/gif,image/jpeg"
          @change="handleFileUpload"
          @keyup.esc=""
        />
        <div class="form-text text-danger">
          Image size must be less than 500ko
        </div>
        <div class="form-text mb-3">Plant's image</div>
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
        ></textarea>
        <div class="form-text text-danger">
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
