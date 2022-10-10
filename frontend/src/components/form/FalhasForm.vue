<template>
    <el-form label-position="top" ref="falhasForm" :model="this.form" :rules="this.rules">
        <el-row :gutter="20">
            <el-col :span="15">
                <el-form-item label="Novo Problema" prop="name">
                    <el-input v-model="form.name" />
                </el-form-item>
            </el-col>
            <el-col :span="9">
                <el-form-item label="Prioridade Padrão" prop="nivel">
                    <el-select v-model="form.nivel" :fit-input-width="true" placeholder="Prioridade">
                        <el-option v-for="(item,index) in this.nivelData" :key="index" :label="nivelLabel[item.nivel].label" :value="index"/>
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
        <el-form-item label="Descrição">
            <el-input v-model="form.description" :rows="2" type="textarea"/>
        </el-form-item>
        <el-form-item label="Soluções">
            <el-tag v-for="(solucao,index) in this.solucoes" :key="index" closable @close="handleCloseTag(index)">
                {{solucao.description}}
            </el-tag>
            <el-input v-if="inputVisible" v-model="inputValue" size="small" style="width:150px" @keyup.enter="this.handleInputConfirm()"/>
            <el-button v-else size="small" @click="this.showInput()">
                + Nova solução
            </el-button>
        </el-form-item>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElInputNumber} from 'element-plus'
import nivel from '../../utils/nivel'
export default {
    name:"FalhasForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElInputNumber
    },
    data(){
        return{
            solucoes:[],
            inputValue:'',
            inputVisible:false,
            form:{
                name:null,
                solutions:null,
                description:null,
                nivel:null,
            },
            rules:{
                name:{
                    required:true,
                    message:'Insira o nome',
                    trigger: 'change'
                },
                solutions:{
                    required:true,
                    message:'Insira no mínimo uma solução',
                    trigger: 'change'
                },
                nivel:{
                    required:true,
                    message:'Insira um nível',
                    trigger: 'change'
                },
            },
            nivelLabel:nivel,
            nivelData:[]
        }
    },
    methods:{
        verifyForm(){
            this.$refs.falhasForm.validate()
            .then(()=>{
                this.form.solutions = this.solucoes
                this.$emit("submit",this.form)
            })
        },
        showInput(){
            this.inputVisible = true
        },
        handleInputConfirm(){
            this.solucoes.push({description:this.inputValue})
            this.inputVisible = false
            this.inputValue = ''
        },
        handleCloseTag(index){
            this.solucoes.splice(index,1)
        },
        reset(){
            this.form={
                name:null,
                solutions:null,
                description:null,
                nivel:null,
            }
            this.solucoes=[]
        }
    },
    created(){
        this.nivelData = this.$store.getters.getNivel
    }
}
</script>