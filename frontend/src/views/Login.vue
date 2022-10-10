<template>
  <div id="page">
    <div id="loginBackgorund">
      <div>
        <img src="../assets/Logo.svg">
        <p style="margin-top: auto;">da Subiter.</p>
      </div>
      <div>
        <p>Bem-vindo</p>
        <p>De volta!</p>
      </div>
      <div>
        <p>Alcance o invisível, Subiter</p>
      </div>
    </div>
    <div id="loginMain">
      <div id="loginContent">
        <section id="welcome">
          <h1>Login</h1>
          <div>
            <img src="../assets/Icons/Info.svg" style="width: 12px; margin:1px 7px;"/>
            <span>
              <p>Olá, amigo! Por favor entre no</p>
              <p>Sistema de Gerenciamento de Controle.</p>
            </span>
          </div>
        </section>
        <LoginForm/>
      </div>
    </div>
  </div>
</template>
 
<script> 
import LoginForm from '../components/form/LoginForm.vue'
export default {
  name:"Login",
  components:{
    LoginForm
  }
}
</script>
 
<style scoped>

#loginBackgorund {
  background-image: url("../assets/BgLogin.svg");
  width: 50%;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  padding: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  color: #fff;
  font-size: 70px;
  font-weight: bold;
}

#loginBackgorund div:first-child{
  display: flex;
}

#loginBackgorund div:last-child{
  display: flex;
  justify-content: center;
}

#loginBackgorund div:first-child,
#loginBackgorund div:last-child{
  font-size: 13px !important;
  font-weight: normal;
}

#loginMain{
  padding: 7rem 0;
  height: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 50%;
}

#loginContent{
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

#welcome{
  display: flex;
  flex-direction: column;
  margin-bottom: 6rem;
}

#welcome h1{
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 1rem;
}

#welcome div{
  display: flex;
  align-items: baseline;
  color: #A5A5A5;
}
</style>


