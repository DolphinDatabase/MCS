<template>
    <el-form label-position="top" ref="equipamentoForm" :model="this.form" :rules="this.rules">
        <el-form-item label="Modelo do Item" prop="modelo">
            <el-input v-model="form.modelo" />
        </el-form-item>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-form-item label="Número de série" prop="num">
                    <el-input v-model="form.num" />
                </el-form-item>
            </el-col>
        </el-row>
        <el-form-item label="Descrição" prop="description">
            <el-input v-model="form.description" :rows="2" type="textarea"/>
        </el-form-item>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElInputNumber} from 'element-plus'

export default {
    name:"EquipamentoForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElInputNumber
    },
    data(){
        return{
            form:{
                num:null,
                model:null,
                description:null,
            },
            rules:{
                num:{
                    required: true, 
                    message: 'Insira o número de série' 
                },
                modelo:{
                    required:true,
                    message:'Insira o modelo',
                    trigger: 'change'
                },
                description:{
                    required:false,
                },
            }
        }
    },
    methods:{
        verifyForm(){
            this.$refs.equipamentoForm.validate()
            .then(()=>{
                this.$emit("submit",this.form)
            })
        }
    }
}
</script>
<style scoped>

</style>