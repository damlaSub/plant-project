<template>
<div className='authentification-header'>
<nav>
<ul  className="auth-navbar">
    <li><img src="myLogo" alt="SOS plant project" className="Navlink logo"/></li>
    <li><a href="/" className="authentification-Navlink Sos">SOS PLANTES</a></li>
    <li className='Navlink-signup'><a href='/signup'> <button className='Navlink-signupbtn'>Créer un compte</button></a></li>
    <li className='Navlink-login'><a  href='/login'><button className="Navlink-loginbtn">Se connecter</button></a></li>
  
         
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
.authentification-header .logo{
    height: 3vw;
 
}
.auth-navbar {
    list-style-type: none;
    margin: 0%;

  }

.authentification-Navlink{

    display: inline-block;
    color: black;
    text-align: center;
    padding: 30px 10px;
    text-decoration: none;
    float: left;
}
.auth-navbar .Sos {
font-size: 30px;
font-weight: 900;
color: #025050;
padding-left: 35%;
}

.auth-navbar .Navlink:hover {
  text-decoration: underline;
  text-decoration-color:   #d5f5e3 ;
  text-decoration-thickness: 9px;
}

.auth-navbar .Sos:hover{
text-decoration: none;
}
</style>
