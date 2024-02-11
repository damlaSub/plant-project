<script>
  import { useVuelidate } from "@vuelidate/core";
  import { helpers } from "@vuelidate/validators";
  import { email, required, maxLength, minLength } from "@vuelidate/validators";

  export default {
    setup() {
      return { v$: useVuelidate({ $autoDirty: true }) };
    },
    data() {
      return {
        inputs: {
          email: null,
          password: null,
          confirm: null,
        },
        showErrorTooltip: false,
      };
    },
    validations() {
      return {
        inputs: {
          email: {
            required,
            email,
          },
          password: {
            required,
            minLength: minLength(8),
            maxLength: maxLength(32),
            pattern: helpers.regex(
              /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,32}$/
            ),
          },
        },
      };
    },
    methods: {
      async handleSignIn() {
        const valid = await this.v$.$validate();
        if (valid) {
          const accountData = {
            email: this.inputs.email,
            password: this.inputs.password,
          };
          localStorage.clear();
          await this.$axios
            .post("/auth/sign-in", accountData)
            .then((response) => {
              if (response.body.role.includes("ADMIN")) {
                this.$router.push("/admin/plants");
              }
              if (response.body.role.includes("USER")) {
                this.$router.push("/user/home");
              }
            })

            .catch((error) => {
              if (error.response.data && error.response.status === 401) {
                (this.showErrorTooltip = true),
                  this.$tooltip.error("tooltip-global", error.response.data);
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
                <img src="/src/assets/plant.png" alt="logo" width="100" />
              </div>
              <h1 class="fs-4 card-title fw-bold mb-4">Sign in</h1>
              <form
                method="POST"
                class="needs-validation login-form"
                autocomplete="off"
                @submit.prevent="handleSignIn"
                novalidate
              >
                <div class="tooltip-wrapper mb-3">
                  <Tooltip v-if="showErrorTooltip" id="tooltip-global" />
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

                <div class="d-flex align-items-center">
                  <div class="mb-2 w-100">
                    <a href="" class="float-start acc">
                      {{ $t("auth.forgot") }}</a
                    >
                  </div>
                  <button
                    type="submit"
                    class="btn btn-save"
                    style="white-space: nowrap"
                  >
                    {{ $t("auth.signIn") }}
                  </button>
                </div>
              </form>
            </div>
            <div class="card-footer py-3 border-0">
              <div class="text-center">
                {{ $t("auth.accQuest") }}
                <RouterLink
                  :to="{ name: 'signup' }"
                  title="signup"
                  class="text-dark route"
                >
                  {{ $t("auth.signUp") }}</RouterLink
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<style></style>

<style>
  main {
    background-color: #f9f5f1;
  }
  .acc,
  .route,
  .acc:hover,
  .route:hover {
    color: #212529;
  }

  .card-footer {
    background-color: #f4ede7;
  }

  /* #tooltip-global.text-bg-danger {
    /* margin-bottom: 150px; */
  /*  display: block;
    width: 100%;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    color: white;
    background-color: red;
    background-clip: padding-box;

    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  } */
</style>
