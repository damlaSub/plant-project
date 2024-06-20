<script>
  import { useVuelidate } from "@vuelidate/core";
  import { helpers } from "@vuelidate/validators";
  import {
    email,
    required,
    maxLength,
    minLength,
    sameAs,
  } from "@vuelidate/validators";

  export default {
    setup() {
      return { v$: useVuelidate({ $autoDirty: true }) };
    },
    data() {
      return {
        fileSystem: import.meta.env.VITE_IMG_BASE_URL,
        inputs: {
          firstName: null,
          lastName: null,
          email: null,
          password: null,
          confirm: null,
        },
        pswRegex: helpers.regex(
          /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,32}$/
        ),
        namesRegex: helpers.regex(/^[a-zA-Z-éàâèêôûîç'’ ]{1,100}$/),
        showErrorTooltip: false,
      };
    },
    validations() {
      return {
        inputs: {
          firstName: {
            required,
            pattern: this.namesRegex,
            minLength: minLength(1),
            maxLength: maxLength(100),
          },
          lastName: {
            required,
            pattern: this.namesRegex,
            minLength: minLength(1),
            maxLength: maxLength(100),
          },
          email: {
            required,
            email,
          },
          password: {
            required,
            pattern: this.pswRegex,
          },
          confirm: {
            required,
            pattern: this.pswRegex,
            sameAs: sameAs(this.inputs.password),
          },
        },
      };
    },

    methods: {
      async submitForm() {
        const valid = await this.v$.$validate();
        if (valid) {
          const accountData = {
            firstName: this.inputs.firstName,
            lastName: this.inputs.lastName,
            email: this.inputs.email,
            password: this.inputs.password,
            confirm: this.inputs.confirm,
          };
          localStorage.clear();
          await this.$axios
            .post("/auth/sign-up", accountData)
            .then((response) => {
              if (response.status === 204) {
                this.v$.$reset();
                this.$router.push("/signin");
              }
            })
            .catch((error) => {
              if (error.response && error.response.status === 400) {
                const fieldErrors = error.response.data.fieldErrors;
                if (
                  fieldErrors &&
                  fieldErrors.email &&
                  fieldErrors.email.includes("UniqueAccountEmail")
                ) {
                  (this.showErrorTooltip = true),
                    this.$tooltip.error(
                      "tooltip-global",
                      this.$t("error.customValid.emailExists")
                    );
                }
              } else {
                (this.showErrorTooltip = true),
                  this.$tooltip.error("tooltip-global", this.$t("error.try"));
              }
            });
        }
      },
      hideTooltip() {
        this.showErrorTooltip = false;
      },
    },
  };
</script>

<template>
  <main class="h-100">
    <div class="container h-100 mt-5">
      <div class="row justify-content-sm-center vh-100">
        <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <div class="card shadow-lg">
            <div class="card-body">
              <div class="text-center my-1">
                <img :src="fileSystem + 'plant.png'" alt="logo" width="100" />
              </div>
              <h1 class="fs-4 card-title fw-bold mb-4">
                {{ $t("title.createAcc") }}
              </h1>
              <form
                class="needs-validation"
                novalidate
                @submit.prevent="submitForm"
                autocomplete="off"
              >
                <div class="tooltip-wrapper mb-3">
                  <Tooltip v-if="showErrorTooltip" id="tooltip-global" />
                </div>
                <div class="mb-3">
                  <input
                    @input="hideTooltip"
                    :class="{ 'is-invalid': v$.inputs.firstName.$error }"
                    id="first-name"
                    type="text"
                    class="form-control"
                    name="first-name"
                    :placeholder="$t('auth.firstN')"
                    v-model="this.inputs.firstName"
                    autofocus
                  />
                  <span class="text-danger" v-if="v$.inputs.firstName.$error">
                    {{ v$.inputs.firstName.$errors[0].$message }}</span
                  >
                </div>

                <div class="mb-3">
                  <input
                    @input="hideTooltip"
                    :class="{ 'is-invalid': v$.inputs.lastName.$error }"
                    id="last-name"
                    type="text"
                    class="form-control"
                    name="last-name"
                    :placeholder="$t('auth.lastN')"
                    v-model="this.inputs.lastName"
                    autofocus
                  />
                  <span class="text-danger" v-if="v$.inputs.lastName.$error">
                    {{ v$.inputs.lastName.$errors[0].$message }}</span
                  >
                </div>

                <div class="mb-3">
                  <input
                    @input="hideTooltip"
                    :class="{ 'is-invalid': v$.inputs.email.$error }"
                    id="email"
                    type="email"
                    class="form-control"
                    name="email"
                    :placeholder="$t('auth.email')"
                    required
                    v-model="this.inputs.email"
                    autofocus
                  />
                  <span class="text-danger" v-if="v$.inputs.email.$error">
                    {{ v$.inputs.email.$errors[0].$message }}</span
                  >
                </div>

                <div class="mb-3">
                  <input
                    @input="hideTooltip"
                    :class="{ 'is-invalid': v$.inputs.password.$error }"
                    id="password"
                    type="password"
                    class="form-control"
                    name="password"
                    :placeholder="$t('auth.psw')"
                    v-model="this.inputs.password"
                  />
                  <span class="text-danger" v-if="v$.inputs.password.$error">
                    {{ v$.inputs.password.$errors[0].$message }}
                  </span>
                </div>
                <div class="mb-3">
                  <div class="mb-2 w-100"></div>
                  <input
                    @input="hideTooltip"
                    id="password-confirm"
                    type="password"
                    class="form-control"
                    :class="{ 'is-invalid': v$.inputs.confirm.$error }"
                    name="password-confirm"
                    :placeholder="$t('auth.confirmPsw')"
                    v-model="this.inputs.confirm"
                  />
                  <span class="text-danger" v-if="v$.inputs.confirm.$error">
                    {{ v$.inputs.confirm.$errors[0].$message }}
                  </span>
                </div>

                <div class="d-flex align-items-center">
                  <div class="form-check">
                    <input
                      @input="hideTooltip"
                      type="checkbox"
                      name="remember"
                      id="remember"
                      class="form-check-input"
                    />
                    <label for="remember" class="form-check-label">{{
                      $t("auth.remember")
                    }}</label>
                  </div>
                  <button type="submit" class="btn ms-auto btn-save">
                    {{ $t("auth.signUp") }}
                  </button>
                </div>
              </form>
            </div>
            <div class="card-footer py-3 border-0">
              <div class="text-center">
                {{ $t("auth.haveAcc") }}

                <RouterLink
                  :to="{ name: 'signin' }"
                  title="signin"
                  class="text-dark route"
                  >{{ $t("auth.signIn") }}</RouterLink
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<style>
  main {
    background-color: #f9f5f1;
  }
  .route {
    text-decoration: underline;
  }
  .form-check-input:checked {
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .card-footer {
    background-color: #f4ede7;
  }
</style>
