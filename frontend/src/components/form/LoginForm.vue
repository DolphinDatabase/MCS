<template>
    <el-form id="loginForm" label-position="top" ref="loginForm" :model="this.form" :rules="this.rules">
        <el-form-item class="email" label="E-mail" prop="login">
            <el-input class="email" v-model="form.login" autofocus />
        </el-form-item>
        <el-form-item class="email" label="Senha" prop="password" style="margin-bottom:0">
            <el-input class="senha" v-model="form.password" type="password" />
        </el-form-item>
        <small><a href="#">Esqueceu a senha?</a></small>
        <div id="loginSubmitGroup">
            <el-button type="primary" @click="submitForm()">Entrar</el-button>
        </div>
    </el-form>
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
                login:'',
                password:''
            },
            rules:{
                login:{
                    required:true,
                    type:'email',
                    message:'Insira seu e-mail',
                    trigger: 'change'
                },
                password:{
                    required:true,
                    message:'Insira sua senha',
                    trigger: 'change'
                }
            }
        }
    },
    methods:{
        submitForm(){
            this.$refs.loginForm.validate()
            .then(()=>{
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
            })
        }
    }
}
</script>
<style scoped>
    #loginForm{ 
        display: flex;
        flex-direction: column;
    }
    
    #loginForm small{
        margin-top: 1rem;
    }
    
    #loginSubmitGroup{
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #loginSubmitGroup .el-button{
        margin-top: 4rem;
        background-color: #0024FF;
        color: #fff;
        width: 200px;
        height: 40px;
    }

</style>