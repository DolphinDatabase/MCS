<template>
    <el-form label-position="top" ref="usuarioForm" :model="this.form" :rules="this.rules">
        <el-form-item label="Nome" prop="name">
            <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="E-mail" prop="email">
            <el-input v-model="form.email" />
        </el-form-item>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-form-item label="CPF/ CNPJ" prop="cpf">
                    <el-input v-model="form.cpf" />
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="Tipo de Usuário" prop="roles">
                    <el-select v-model="form.roles" :fit-input-width="true" placeholder="Tipo de Usuário">
                        <el-option v-for="item in this.rolesData" :key="item.id" :label="rolesLabel[item.nivel].label" :value="item.id"/>
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElInputNumber} from 'element-plus'
import roles from '../../utils/roles'
export default {
    name:"UsuarioForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElInputNumber
    },
    data(){
        return{
            form:{
                name:null,
                email:null,
                cpf:null,
                roles:null
            },
            rules:{
                name:{
                    required:true,
                    message:'Insira o nome',
                    trigger: 'change'
                },
                email:{
                    required:true,
                    message:'Insira o e-mail',
                    trigger: 'change'
                },
                cpf:[
                    {
                        required:true,
                        message:'Insira o cpf/cnpj',
                        trigger: 'change'
                    },{ 
                        min: 11,
                        max: 14,
                        message: 'Documento inválido', 
                        trigger: 'blur' 
                    },
                ],
                roles:{
                    required:true,
                    message:'Insira um nível',
                    trigger: 'change'
                }
            },
            rolesLabel:roles,
            rolesData:[]
        }
    },
    methods:{
        verifyForm(){
            this.$refs.usuarioForm.validate()
            .then(()=>{
                this.$emit("submit",this.form)
            })
        }
    },
    created(){
        this.rolesData = this.$store.getters.getRoles
    }
}
</script>