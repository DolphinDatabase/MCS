<template>
    <div id="drop" :class="displayForm?'desactive':'active'">
        <el-form label-position="top">
            <el-form-item label="Problemas conhecidos">
                <el-select v-model="problema" :fit-input-width="true" placeholder="Selecione um Problema" size="large" >
                    <el-option v-for="item in this.conhecidos" :key="item.id" :label="item.name" :value="item.id"/>
                </el-select>
            </el-form-item>
        </el-form>
        <span id="formLink" @click="this.displayForm = true">
            Criar Novo 
            <el-icon class="icon" :size="10">
              <Plus />
            </el-icon>
        </span>
    </div>
    <div id="problemaForm" :class="displayForm?'active':'desactive'">
        <el-form label-position="top" :model="form">
            <el-form-item label="Nome">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="Risco">
                <el-select v-model="form.nivel" :fit-input-width="true" placeholder="Risco" size="large">
                    <el-option v-for="(item,index) in this.nivel" :key="index" :label="item.label" :value="item.value"/>
                </el-select>
            </el-form-item>
            <h5>Soluções para o problema</h5>
            <el-form-item v-for="(item,index) in this.form.solutions" :key="index" :label="'Solução '+(index+1)">
                <el-input v-model="item.description" />
            </el-form-item>
            <span id="formLink" @click="this.form.solutions.push({description:''})">
                Nova Solução 
                <el-icon class="icon" :size="10">
                    <Plus />
                </el-icon>
            </span>
        </el-form>
    </div>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElSelect,ElOption,ElCol,ElRow} from 'element-plus'
import nivel from '../utils/nivel'
export default {
    name:"ProblemaForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElSelect,
        ElOption,
        ElCol,
        ElRow
    },
    data(){
        return{
            conhecidos:[],
            nivel:nivel,
            problema:null,
            displayForm:false,
            form:{
                name:'',
                nivel:'',
                solutions:[
                    {
                        description:''
                    }
                ]
            }
        }
    },
    async mounted(){
        await this.$store.dispatch("listProblema")
        this.conhecidos = this.$store.getters.getAllProblemas
    }
}
</script>
<style scoped>

    #formLink{
        color: #000;
        transition: 0.5s;
        float: right;
        cursor: pointer;
    }

    #formLink:hover{
        color: #0815A3;
        transition: 0.5s;
    }

    .active{
        display: block;
    }

    .desactive{
        display: none;
    }
</style>