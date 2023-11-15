<script>
  import { useVuelidate } from "@vuelidate/core";
  import { Toast } from "bootstrap";
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
        // account: null,
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
      async handleSignIn(event) {
        const valid = await this.v$.$validate();
        if (valid) {
          const accountData = {
            email: this.inputs.email,
            password: this.inputs.password,
          };
          await this.$axios
            .post("/sign-in", accountData)
            .then((response) => {
              // const isAdmin = accountData.email.endsWith("@test.com");
              const token = response.body.token;
              localStorage.setItem("token", token);
              this.$axios.defaults.headers.common[
                "Authorization"
              ] = `Bearer ${token}`;
              //$axios.get("http://your-backend-api-endpoint");
              this.$router.push("/admin/plants");

              // if (isAdmin) {
              //   this.$router.push("/admin/plants");
              // } else {
              //   this.$router.push("/");
              // }
            })
            .catch((error) => {
              console.log(error);
              //console.log(error.response.data);
            });
        }
      },
      // async initAccount() {
      //   const resp = await this.$axios.get("/accounts/${id}");
      //   this.account = resp.body;
      //   console.log(this.account);
      // },
    },
  };
</script>
<template>
  <main class="h-100">
    <div class="container h-100">
      <div class="row justify-content-sm-center vh-100">
        <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <div class="card shadow-lg">
            <div class="card-body">
              <div class="text-center my-1">
                <img src="/src/assets/myLogo.png" alt="logo" width="100" />
              </div>
              <h1 class="fs-4 card-title fw-bold mb-4">Log in</h1>
              <form
                method="POST"
                class="needs-validation login-form"
                autocomplete="off"
                @submit.prevent="handleSignIn"
                novalidate
              >
                <!-- error toast -->

                <!-- error toast -->

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
                    <a href="" class="float-start"> Forgot Password? </a>
                  </div>
                  <button type="submit" class="btn ms-auto submit">
                    Login
                  </button>
                </div>
              </form>
            </div>
            <div class="card-footer py-3 border-0">
              <div class="text-center">
                Don't have an account?
                <a href="/signup" class="text-dark">Sign up</a>
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
  a {
    text-decoration: none;
    color: #212529;
  }
  a:hover {
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
