<template>
    <el-form label-position="top" ref="ProblemForm" :model="this.form" :rules="this.rules">
        <div v-if="this.type==0">
            <el-form-item label="Falha" prop="known">
                <el-select v-model="form.known" :fit-input-width="true" placeholder="Problema conhecido">
                    <el-option v-for="k in this.knows" :key="k.id" :value="k.id" :label="k.name"/>
                </el-select>
            </el-form-item>
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-form-item label="Comprimeto (mm)" prop="width">
                        <el-input v-model="form.width" type="number"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="Altura (mm)" prop="height">
                        <el-input v-model="form.height" type="number"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="Profundidado (mm)" prop="depth">
                        <el-input v-model="form.depth" type="number"/>
                    </el-form-item>
                </el-col>
            </el-row>
        </div>
        <div v-if="this.type==1">
            <el-row :gutter="20">
                <el-col :span="15">
                    <el-form-item label="Nova Falha" prop="name">
                        <el-input v-model="form.name" />
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="Prioridade Padrão" prop="nivel">
                        <el-select v-model="form.nivel" :fit-input-width="true" placeholder="Prioridade">
                            <el-option v-for="(item,index) in this.nivelData" :key="index" :label="item.label" :value="index"/>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-form-item label="Comprimeto (mm)" prop="width">
                        <el-input v-model="form.width" type="number"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="Altura (mm)" prop="height">
                        <el-input v-model="form.height" type="number"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="Profundidade (mm)" prop="depth">
                        <el-input v-model="form.depth" type="number"/>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="Soluções">
                <el-tag v-for="(solucao,index) in this.solucoes" :key="index" closable @close="handleCloseTag(index)">
                    {{solucao.description}}
                </el-tag>
                <el-input v-if="inputVisible" v-model="inputValue" size="small" class="tagInput" @keyup.enter="this.handleInputConfirm()"/>
                <el-button v-else size="small" @click="this.showInput()">
                    + Nova solução
                </el-button>
            </el-form-item>
        </div>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElInputNumber,ElSelect,ElOption} from 'element-plus'
import nivel from '../../utils/nivel'
export default {
    name:"ProblemForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElInputNumber,
        ElSelect,
        ElOption
    },
    props:{
        type:Number
    },
    data(){
        return{
            knows:[],
            nivelData:nivel,
            inputVisible:false,
            inputValue:null,
            form:{
                known:null,
                nivel:null,
                name:null,
                width:null,
                height:null,
                depth:null
            },
            solucoes:[],
            rules:{
                known:{
                    required: true,
                    message: 'Insira uma falha conhecida',
                    trigger: 'change',
                },
                name:{
                    required:true,
                    message:'Insira a Falha',
                    trigger: 'change'
                },
                width:{
                    required:true,
                    message:'Insira o comprimeto',
                    trigger: 'change'
                },
                height:{
                    required:true,
                    message:'Insira a altura',
                    trigger: 'change'
                },
                depth:{
                    required:true,
                    message:'Insira a profundidade',
                    trigger: 'change'
                },
                nivel:{
                    required:true,
                    message:'Insira uma prioridade',
                    trigger: 'change'
                }
            }
        }
    },
    methods:{
        showInput(){
            this.inputVisible = true
        },
        handleInputConfirm(){
            this.solucoes.push({description:this.inputValue})
            console.log(this.solucoes)
            this.inputVisible = false
            this.inputValue = ''
        },
        handleCloseTag(index){
            this.solucoes.splice(index,1)
        },
        verifyForm(){
            this.$refs.ProblemForm.validate()
            .then(()=>{
                this.form['solutions'] = this.solucoes
                this.$emit('submit',this.form)
            })
        },
        reset(){
            this.form = {
                known:null,
                nivel:null,
                name:null,
                width:null,
                height:null,
                depth:null
            }
        }
    },
    async created(){
        await this.$store.dispatch("listProblema")
        this.knows = this.$store.getters.getAllProblemas
        console.log(this.knows)
    }
}
</script>
<style scoped>
    #desactive{
        display: none;
    }

    #active{
        display: block;
    }

    .tagInput{
        width: 150px;
    }
</style>