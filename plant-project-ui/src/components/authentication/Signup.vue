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
      return { v$: useVuelidate() };
    },
    data() {
      return {
        inputs: {
          firstName: null,
          lastName: null,
          email: null,
          password: null,
          // confirm: null,
        },
      };
    },
    validations() {
      return {
        inputs: {
          firstName: {
            required,
            maxLength: maxLength(250),
          },
          lastName: {
            required,
            maxLength: maxLength(250),
          },
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
      async submitForm(event) {
        const valid = await this.v$.$validate();
        if (valid) {
          const accountData = {
            firstName: this.inputs.firstName,
            lastName: this.inputs.lastName,
            email: this.inputs.email,
            password: this.inputs.password,
          };
          localStorage.clear();
          await this.$axios
            .post("/sign-up", accountData)
            .then((response) => {
              this.v$.$reset();
              console.log("response", response);
              this.$router.push("/signin");
            })
            .catch((error) => {
              console.log(error);
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
              <h1 class="fs-4 card-title fw-bold mb-4">Create an account</h1>
              <form
                class="needs-validation"
                novalidate
                @submit.prevent="submitForm"
                autocomplete="off"
              >
                <div class="mb-3">
                  <input
                    :class="{ 'is-invalid': v$.inputs.firstName.$error }"
                    id="first-name"
                    type="text"
                    class="form-control"
                    name="first-name"
                    placeholder="first name*"
                    v-model="this.inputs.firstName"
                    autofocus
                  />
                  <span class="text-danger" v-if="v$.inputs.firstName.$error">
                    {{ v$.inputs.firstName.$errors[0].$message }}</span
                  >
                </div>

                <div class="mb-3">
                  <input
                    :class="{ 'is-invalid': v$.inputs.lastName.$error }"
                    id="last-name"
                    type="text"
                    class="form-control"
                    name="last-name"
                    placeholder="last name*"
                    v-model="this.inputs.lastName"
                    autofocus
                  />
                  <span class="text-danger" v-if="v$.inputs.lastName.$error">
                    {{ v$.inputs.lastName.$errors[0].$message }}</span
                  >
                </div>

                <div class="mb-3">
                  <input
                    :class="{ 'is-invalid': v$.inputs.email.$error }"
                    id="email"
                    type="email"
                    class="form-control"
                    name="email"
                    placeholder="e-mail*"
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

                <!-- <div class="mb-3">
                  <div class="mb-2 w-100"></div>
                  <input
                    id="password-confirm"
                    type="password"
                    class="form-control"
                    name="password-confirm"
                    placeholder="confirm password*"
                    v-model="this.inputs.confirm"
                  />
                  <span class="text-danger" v-if="v$.inputs.confirm.$error">
                    {{ v$.inputs.confirm.$errors[0].$message }}
                  </span>
                </div> -->

                <div class="d-flex align-items-center">
                  <div class="form-check">
                    <input
                      type="checkbox"
                      name="remember"
                      id="remember"
                      class="form-check-input"
                    />
                    <label for="remember" class="form-check-label"
                      >Remember Me</label
                    >
                  </div>
                  <button type="submit" class="btn ms-auto submit">
                    Sign up
                  </button>
                </div>
              </form>
            </div>
            <div class="card-footer py-3 border-0">
              <div class="text-center">
                Already have an account?
                <a href="/signin" class="text-dark">Sign in</a>
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
  .form-check-input:checked {
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .card-footer {
    background-color: #f4ede7;
  }
  #email:focus,
  #password:focus,
  /* #password-confirm:focus, */
  #remember:focus {
    border-color: #355e3b;
    box-shadow: 0 0 0 0.2rem #355e3b;
  }
</style>
