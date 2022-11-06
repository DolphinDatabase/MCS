<template>
    <el-form label-position="top" ref="administrativoForm" :model="this.form" :rules="this.rules">
      <el-form-item label="Data do Serviço" prop="service">
        <el-date-picker v-model="this.form.service" type="date"/>
      </el-form-item>
      <el-form-item label="Responsável pelo Serviço" prop="responsible">
        <el-select v-model="this.form.responsible" :fit-input-width="true" placeholder="Responsável">
          <el-option v-for="item in this.responsibles" :key="item.id" :label="item.name" :value="item.id"/>
        </el-select>
      </el-form-item>
    </el-form>
</template>

<script>
import {ElForm,ElFormItem,ElInput,ElDatePicker} from 'element-plus'
export default {
    name:"AdministrativoForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElDatePicker
    },
    data(){
        return{
            form:{
              service:null,
              responsible:null
            },
            rules:{
                responsible:{ 
                  required:true,
                  message:'Insira um administrador responsável pelo chamado',
                  trigger: 'change'
                },
                service: {
                  type: 'date',
                  required: true,
                  message: 'Insira uma data para executar o serviço',
                  trigger: 'change',
                },
            },
            responsibles:[]
        }
    },
    methods:{
      verifyForm(){
        this.$refs.administrativoForm.validate()
        .then(()=>{
          this.$emit("submit",this.form)
        })
      },
      reset(){
        this.form = {
          service:null,
          responsible:null
        }
      }
    },
    async created(){
      await this.$store.dispatch("listUsuarios")
      this.responsibles = this.$store.getters.getResponsibles
    }  
}

</script>

<style>
.inputData {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.inputData .block {
  padding: 30px 0;
  text-align: left;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.inputData .block:last-child {
  border-right: none;
}
.inputData .adm_info {
  display: block;
  font-size: 14px;
  margin-bottom: 20px;
}
.adm_info{
  font-family: 'Nunito';
  font-style: normal;
  font-weight: 700;
  color: #757575;
}
</style>