<script>
  import { useVuelidate } from "@vuelidate/core";
  import {
    helpers,
    maxLength,
    minValue,
    required,
  } from "@vuelidate/validators";
  import { useRoute } from "vue-router";
  export default {
    setup() {
      return { route: useRoute(), v$: useVuelidate({ $autoDirty: true }) };
    },
    data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        sizeFile: true,
        id: this.route.params.id,
        namesRegex: helpers.regex(/^[a-zA-Z-éàâèêôûîç'’ ]{1,100}$/),
        hydrationLevels: [],
        sunlightLevels: [],
        inputs: {
          commonName: null,
          latinName: null,
          description: null,
          image: undefined,
          hydrationId: 0,
          sunlightId: 0,
        },
      };
    },
    validations() {
      return {
        inputs: {
          commonName: {
            required,
            pattern: this.namesRegex,
            maxLength: maxLength(100),
          },
          latinName: {
            required,
            pattern: this.namesRegex,
            maxLength: maxLength(200),
          },
          description: { required, maxLength: maxLength(1000) },
          hydrationId: { required, minValue: minValue(1) },
          sunlightId: { required, minValue: minValue(1) },
          file: {
            maxValue: (file) => {
              return file !== undefined && file.size < 512000;
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
            formData.append("file", this.inputs.file);
          }
          formData.append("commonName", this.inputs.commonName);
          formData.append("latinName", this.inputs.latinName);
          formData.append("description", this.inputs.description);
          formData.append("hydrationId", this.inputs.hydrationId);
          formData.append("sunlightId", this.inputs.sunlightId);
          const resp = await this.$axios.put(
            `/plants/admin/${this.id}`,
            formData
          );
          if (resp.status == 204) {
            this.v$.$reset();
            this.$toast.success("toast-global", this.$t("success.update"));
            this.$router.push("/admin/plants");
          } else {
            console.log(resp.status);
            this.$toast.error("toast-global", this.$t("error.try"));
          }
        }
      },
      async handleFileUpload(event) {
        if (event.target.files[0] != null) {
          this.inputs.file = event.target.files[0];
        }
      },
      async getPlantForUpdate() {
        const response = await this.$axios.get(
          `/plants/admin/${this.id}/for-update`
        );
        this.inputs = response.body;
        const data = response.body;
        this.inputs.commonName = data.commonName;
        this.inputs.latinName = data.latinName;
        this.inputs.description = data.description;
        this.inputs.hydrationId = data.hydration.id;
        this.inputs.sunlightId = data.sunlight.id;
        this.inputs.file = data.image;
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
      this.getPlantForUpdate();
    },
  };
</script>

<template>
  <div class="mt-5 mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">{{ $t("title.updatePlant") }}</h1>
    <div class="col-md-2">
      <img :src="fileSystem + inputs.image" class="rounded img-fluid" />
    </div>
    <div class="col-md-8">
      <form novalidate @submit.prevent="updatePlant">
        <div class="col-md-4 my-3">
          <label for="input-name" class="form-label required" maxlength="100">{{
            $t("title.common")
          }}</label>
          <input
            :class="{ 'is-invalid': v$.inputs.commonName.$error }"
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
            {{ $t("error.common") }}
          </span>
          <span id="input-name-helptext" class="fw-light" v-else>
            {{ $t("helper.common") }}
          </span>
        </div>
        <div class="col-md-8">
          <label for="latin" class="form-label required" maxlength="200">{{
            $t("title.latin")
          }}</label>
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
            {{ $t("error.latin") }}
          </span>
          <span id="latin-helptext" class="fw-light" v-else>
            {{ $t("helper.latin") }}
          </span>
        </div>

        <div class="col-md-4 my-3">
          <label for="hydrationId" class="form-label required">{{
            $t("title.hydration")
          }}</label>
          <select
            :class="{ 'is-invalid': v$.inputs.hydrationId.$error }"
            v-model.number="inputs.hydrationId"
            name="hydrationId"
            class="form-select"
            id="hydrationId"
          >
            <option selected disabled value="0">
              {{ $t("title.selectHyd") }}
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
            {{ $t("error.required") }}
          </span>
          <span
            id="hydratation-helptext"
            class="fw-light"
            v-else="v$.inputs.hydrationId.$error"
          >
            {{ $t("helper.hydration") }}
          </span>
        </div>
        <div class="col-md-4 my-3">
          <label for="sunlightId" class="form-label required">{{
            $t("title.sunlight")
          }}</label>
          <select
            :class="{ 'is-invalid': v$.inputs.sunlightId.$error }"
            v-model.number="inputs.sunlightId"
            name="sunlightId"
            class="form-select"
            id="sunlightId"
          >
            <option selected disabled value="0">
              {{ $t("title.selectSun") }}
            </option>
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
            {{ $t("error.required") }}
          </span>
          <span
            id="sunlight-helptext"
            class="fw-light"
            v-else="v$.inputs.sunlightId.$error"
          >
            {{ $t("helper.sunlight") }}
          </span>
        </div>
        <div class="col-md-4 my-3"></div>

        <div class="col-md-4 my-3">
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
            {{ $t("error.img") }}
          </div>
          <div v-else id="image-helptext" class="fw-light">Plant's image</div>
        </div>
        <div class="col-8">
          <label
            for="description"
            class="form-label required"
            maxlength="1000"
            >{{ $t("title.desc") }}</label
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
          <span
            class="form-text text-danger"
            v-if="v$.inputs.description.$error"
          >
            {{ $t("error.desc") }}
          </span>
          <span id="description-helptext" class="fw-light" v-else>
            {{ $t("helper.desc") }}
          </span>
        </div>
        <div class="d-grid d-md-flex justify-content-md-end">
          <button type="submit" class="btn-save">{{ $t("btn.save") }}</button>
        </div>
      </form>
    </div>
  </div>
</template>
