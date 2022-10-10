<template>
    <el-form label-position="top" ref="orcamentoForm" :model="this.form" :rules="this.rules">
        <div class="inputNum">
            <el-form-item label="Valor" prop="total">
                <el-input v-model="form.total" type="number"/>
            </el-form-item>
        </div>
        <el-form-item label="Descrição" prop="description">
            <el-input v-model="form.description" :rows="2" type="textarea"/>
        </el-form-item>    
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElInputNumber} from 'element-plus'

export default {
    name:"EqChamadoForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElInputNumber
    },
    data(){
        return{
            form:{
                total:null,
                description:null,
            },
            rules:{
                total:{ 
                    required: true, 
                    message: 'Insira o valor',
                    trigger: 'change'
                }
            }
        }
    },
    methods:{
        verifyForm(){
            this.$refs.orcamentoForm.validate()
            .then(()=>{
                this.$emit("submit",this.form)
            })
        }
    }
}
</script>

<style scoped>

.inputNum {
    display: flex; 
    justify-content: center; 
    flex-direction: column; 
    align-items: center;
}

</style>