<template>
  <div class="authentification-header">
    <nav>
      <ul class="auth-navbar">
        <li>
          <img src="myLogo" alt="SOS plant project" class="navlink logo" />
        </li>
        <li>
          <a href="/" class="authentification-navlink sos">SOS PLANTES</a>
        </li>
        <li class="navlink-signup">
          <a href="/signup">
            <button class="navlink-signupbtn">Créer un compte</button></a
          >
        </li>
        <li class="navlink-login">
          <a href="/login"
            ><button class="navlink-loginbtn">Se connecter</button></a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<style scoped>
  .authentification-header {
    margin: 0%;
    position: relative;
    z-index: 10;
    height: 96px;
    border-bottom: 1px solid #000;
    background-color: #f9f5f1;
  }
  .authentification-header .logo {
    height: 3vw;
  }
  .auth-navbar {
    list-style-type: none;
    margin: 0%;
  }

  .authentification-navlink {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 30px 10px;
    text-decoration: none;
    float: left;
  }
  .auth-navbar .sos {
    font-size: 30px;
    font-weight: 900;
    color: #025050;
  }

  .auth-navbar .navlink:hover {
    text-decoration: underline;
    text-decoration-color: #d5f5e3;
    text-decoration-thickness: 9px;
  }

  .auth-navbar .sos:hover {
    text-decoration: none;
  }
</style>
