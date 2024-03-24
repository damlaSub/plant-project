<script>
  import { useVuelidate } from "@vuelidate/core";
  import { helpers } from "@vuelidate/validators";
  import { maxLength, minValue, required } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate({ $autoDirty: true }) };
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
        namesRegex: helpers.regex(/^[a-zA-Z-éàâèêôûîç'’ ]{1,100}$/),
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
          this.$toast.success("toast-global", this.$t("success.create"));
          this.$router.push("/admin/plants");
        } else {
          this.$toast.error("toast-global", this.$t("error.err"));
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
    <h1 class="fs-4 card-title fw-bold mb-4">{{ $t("title.createPlant") }}</h1>
    <form class="row g-3 needs-validation" novalidate @submit.prevent="submit">
      <div class="col-md-4">
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
        <span class="form-text text-danger" v-if="v$.inputs.commonName.$error">
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
      <div class="col-md-4">
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
        <span class="form-text text-danger" v-if="v$.inputs.hydrationId.$error">
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
      <div class="col-md-4">
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
        <span class="form-text text-danger" v-if="v$.inputs.sunlightId.$error">
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
      <div class="col-md-4">
        <label for="image" class="form-label required" maxlength="100">{{
          $t("title.img")
        }}</label>
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
          {{ $t("error.img") }}
        </span>
        <span id="image-helptext" class="fw-light" v-else>{{
          $t("helper.img")
        }}</span>
      </div>
      <div class="col-12">
        <label for="description" class="form-label required" maxlength="1000">{{
          $t("title.desc")
        }}</label>
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
</template>
