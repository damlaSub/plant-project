<script setup>
import AuthentificationHeader from "../components/AutenticationHeader.vue"
import myLogo from "../assets/myLogo.png"
</script>

<template>
  <section>
  <AuthentificationHeader/>
  
  <form  className='Login-form' action="" method="post">

  <div className="Login-container">
  <img src={{myLogo}} className='Login-logo' />
  <br/>
  <label ><b>Connectez-vous</b></label>
    <br/>
   
    <input className='Username-input' type="text" placeholder="Enter nom d'utilisateur*" name="username" required/>
    <br/>
   
    <input className='Email-input' type="password" placeholder="Enter le mot de passe*" name="psw" required/>

    <button className='Login-button' type="submit">Se connecter</button>
    <span className="Psw"><a className='Forget-psw' href="#">Mot de passe oublié?</a></span>
    <span className="Login-signup"><a className='Login-sign' href="/signup">CREEZ UN COMPTE </a></span>
    

  </div>

 
</form> 
</section>
</template>

<style>
.Login-form {
    width: 100%;
    height: auto;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;   
    background-color: #f9f5f1;
}


.Login-container {
    width: 500px;
    height: 480px;
    border-radius: 40px;
    box-shadow: 0 14px 28px ;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;
    margin-top: 4vw;
    margin-bottom: 4vw;
   background-color: #f9f5f1;
}

.Login-logo {
height: 5vw;
width: 5vw;
margin-bottom: 0.2vw;
margin-top: 1.8vw;
}
.Username-input, .Email-input {
    border-radius: 7px;
    border-color: #355E3B;
    width: 27vw;
    margin: 1.2vw 15vw;
    padding: 10px 14px;
}
.Login-button {
    color: white;
    background-color:#355E3B;
    border-color: #355E3B;
    border-radius: 12px;
    padding: 8px 18px;
    margin-bottom: 25px;
       
}
.Login-button:hover{
    color:#025050;
    background-color: white;
    border-color:#355E3B;;
    cursor: pointer;
}
.Forget-psw {
color: black;
}


.Login-signup{
    padding-top: 32PX;
}

.Login-sign{
text-decoration: none;
color: black;
}
</style>
