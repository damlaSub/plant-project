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
                novalidate=""
                autocomplete="off"
              >
                <div class="mb-3">
                  <input
                    id="email"
                    type="email"
                    class="form-control"
                    name="email"
                    value=""
                    placeholder="e-mail address*"
                    required
                    autofocus
                  />
                  <div class="invalid-feedback">Email is invalid</div>
                </div>

                <div class="mb-3">
                  <input
                    id="password"
                    type="password"
                    class="form-control"
                    name="password"
                    placeholder="password*"
                    required
                  />
                  <div class="invalid-feedback">Password is required</div>
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
</style>
