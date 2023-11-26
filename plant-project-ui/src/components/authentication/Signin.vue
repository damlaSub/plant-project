<script>
  import { useVuelidate } from "@vuelidate/core";
  import {
    email,
    required,
    maxLength,
    minLength,
    // sameAs,
  } from "@vuelidate/validators";

  export default {
    setup() {
      return { v$: useVuelidate({ $autoDirty: true }) };
    },
    data() {
      return {
        inputs: {
          email: null,
          password: null,
          // confirm: null,
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
          },
          // confirm: {
          //   required,
          //   sameAs: sameAs(this.inputs.password),
          // },
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
            .post("/sign-in", accountData)
            .then((response) => {
              if (response.body.role.includes("_ADMIN")) {
                this.$router.push("/admin/plants");
              } else {
                this.$router.push("/");
              }
            })
            .catch((error) => {
              if (error.response.data != undefined) {
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
                    placeholder="e-mail*"
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
                    placeholder="password*"
                    v-model="this.inputs.password"
                  />
                  <span class="text-danger" v-if="v$.inputs.password.$error">
                    {{ v$.inputs.password.$errors[0].$message }}
                  </span>
                </div>

                <div class="d-flex align-items-center">
                  <div class="mb-2 w-100">
                    <a href="" class="float-start acc"> Forgot Password? </a>
                  </div>
                  <button type="submit" class="btn ms-auto submit">
                    Sign&nbsp;in
                  </button>
                </div>
              </form>
            </div>
            <div class="card-footer py-3 border-0">
              <div class="text-center">
                Don't have an account?
                <RouterLink
                  :to="{ name: 'signup' }"
                  title="signup"
                  class="text-dark route"
                  >Sign up</RouterLink
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
  .route {
    text-decoration: none;
    color: #212529;
  }
  .acc:hover,
  .route:hover {
    color: #355e3b;
  }
  .submit {
    color: white;
    background-color: #355e3b;
    border-color: #355e3b;
    border-radius: 12px;
    padding: 8px 18px;
  }
  .submit:hover {
    color: white;
    border-color: white;
    background-color: #355e3b;
    cursor: pointer;
  }

  .card-footer {
    background-color: #f4ede7;
  }
  #email:focus,
  #password:focus {
    border-color: #355e3b;
    box-shadow: 0 0 0 0.2rem #355e3b;
  }
</style>
