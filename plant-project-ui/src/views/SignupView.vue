<script setup>
import AuthentificationHeader from "../components/AutenticationHeader.vue"

</script>
<template>
   <section>
    <AuthentificationHeader/>
    <form  className='Signup-form' action="" method="post">

  <div className="Signup-container">
  <img src={myLogo} className='Signup-logo' />
  <br/>
  <label ><b>Créez un compte</b></label>
    <br/>
   
    <input className='Signup-username' type="text" placeholder="Enter nom d'utilisateur*" name="signupusername" required/>
    <br/>

    <input className='Signup-email' type="email" placeholder="Enter une adresse mail*" name="signupemail" required/>
    <br/>
   
    <input className='Signup-psw' type="password" placeholder="Enter le mot de passe*" name="signuppsw" required/>
<br/>
<input className='Confirm-psw' type="password" placeholder="Confirmer le mot de passe*" name="confirmpsw" required/>
    <button className='Signup-button' type="submit">S'inscrire</button>
<p></p>
<span className='Signup-login'> Vous avez déja un compte? <a className='Signup-log' href="/login">CONNECTEZ VOUS</a></span>
    
  </div>

 
</form> 
</section>
</template>
  
  <style>
  .Signup-form {
    width: 100%;
    height: auto;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;
    background-color: #f9f5f1;   
}


.Signup-container {
    width: 500px;
    height: 580px;
    border-radius: 40px;
    box-shadow: 0 14px 28px ;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;
    background-color: #f9f5f1;
    margin-top: 3vw;
    margin-bottom: 3vw;
}

.Signup-logo {
height: 5vw;
width: 5vw;
margin-bottom: 0.2vw;
margin-top: 1.8vw;
}
.Signup-username, .Signup-email, .Signup-psw, .Confirm-psw {
    border-radius: 7px;
    border-color: #355E3B;
    width: 27vw;
    margin: 0.6vw 15vw;
    padding: 10px 14px;
}
.Signup-button {
   

    color: white;
    background-color:#355E3B;
    border-color: #355E3B;
    border-radius: 12px;
    padding: 8px 18px;
    margin-top: 25px;
}
.Signup-button:hover{
    color:#025050;
    background-color: white;
    border-color:#355E3B;;
    cursor: pointer;
}

.Signup-login{
margin-bottom: 2vw;
margin-top: 0;
}

.Signup-log{
text-decoration: none;
color: black;
}
  </style>
  