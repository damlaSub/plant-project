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
    },
    beforeMount() {
      this.initWaters();
      this.initSuns();
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Update plant</h1>
    image, name, latin name, water & sun
    <form class="row g-3" novalidate @submit.prevent="">
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
