<template>
    <el-form label-position="top" :model="form">
        <el-form-item label="Nome">
            <el-input v-model="form.name" />
        </el-form-item>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-form-item label="Tipo">
                    <el-select v-model="form.roles" :fit-input-width="true" placeholder="Selecione o tipo de usuário" size="large">
                        <el-option label="Administrador" :value="1"/>
                        <el-option label="Suporte" :value="2"/>
                        <el-option label="Cliente" :value="3"/>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="E-mail">
                    <el-input v-model="form.email" :rows="1" type="textarea"/>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>

<script>
import {ElForm,ElFormItem,ElInput,ElSelect} from 'element-plus'
export default {
    name:"UsuarioForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElSelect
    },
    props:{
        editId:{
            type:Number,
            required: false
        }
    },
    data(){
        return{
            form:{
                name:'',
                email:'',
                password:'',
                roles:null
            }
        }
    }
}
</script>
            
