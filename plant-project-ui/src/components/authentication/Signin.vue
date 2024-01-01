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
              //if (response.body.role.includes("_ADMIN")) {
              this.$router.push("/admin/plants");
              console.log(token.getRole);
            })

            .catch((error) => {
              if (error.response.data && error.response.data != undefined) {
                this.$toast.error("toast-global", error.response.data);
              }
            });
        }
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
                <img src="/src/assets/logo.png" alt="logo" width="100" />
              </div>
              <h1 class="fs-4 card-title fw-bold mb-4">Sign in</h1>
              <form
                method="POST"
                class="needs-validation login-form"
                autocomplete="off"
                @submit.prevent="handleSignIn"
                novalidate
              >
                <div class="mb-3">
                  <input
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
</style>
