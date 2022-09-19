<template>
  <div id="loginpage">
    <section id="login">
      <LoginForm/>
    </section>
  </div>
</template>
 
<script>
import LoginForm from '../components/LoginForm.vue'
export default {
  name:"Login",
  components:{
    LoginForm
  }
}
</script>
 
<style>
 
#loginpage{
  width: 100%;
  height: 100%;
  display:flex;
  justify-content: end;
}

#login{
  border-radius: 60px 0 0 60px;
  width: 55%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #fff;
}

*, /*resetar o estilo da página*/
*::after,
*::before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  text-decoration: none;
}
 
.row-login {
  margin-left: 20;
}
 
.left-login {
  background-color: #FFFFFF;
}
 
.title-login {
font-family: 'Roxborough CF';
font-weight: 700;
font-size: 36px;
line-height: 43px;
color: #407BFF;
text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
</style>


