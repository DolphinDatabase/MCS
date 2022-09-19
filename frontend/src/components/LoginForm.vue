<template>
    <div id="myForm">
        <h2 class="title-login">Login</h2>
        <el-form label-position="top" :model="form">
            <el-form-item label="E-mail">
                <el-input v-model="form.email"/>
            </el-form-item>
            <el-form-item label="Senha">
                <el-input v-model="form.password" type="password"/>
            </el-form-item>
            <div id="footer">
                <small><a href="#">Esqueceu sua senha?</a></small>
                <el-button type="primary" @click="submitForm()">Entrar</el-button>
            </div>
        </el-form>
    </div>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElButton} from 'element-plus'
export default {
    name:"LoginForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElButton
    },
    data(){
        return{
            form:{
                email:'',
                password:''
            }
        }
    },
    methods:{
        submitForm(){
            this.$store.dispatch("createSession",this.form)
            .then(()=>{
                this.$router.push("/")
            })
            .catch((err)=>{
                this.form={
                    email:'',
                    password:''
                }
                alert(err)
            })
        }
    }
}
</script>
<style scoped>

#myForm{
    width: 200px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.title-login {
    font-family: 'Roxborough CF';
    font-weight: 700;
    font-size: 36px;
    line-height: 43px;
    color: #407BFF;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    margin-bottom: 50px;
}

#footer{
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>