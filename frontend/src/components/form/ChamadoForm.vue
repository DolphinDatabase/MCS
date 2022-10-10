<template>
    <el-form label-position="top" :model="formIdentificacao" :id="step==0?'active':'desactive'" ref="formIdentificacao" :rules="this.rulesIdentificacao">
        <el-form-item label="Nome" prop="name">
            <el-input v-model="formIdentificacao.name" />
        </el-form-item>
        <el-form-item label="Descrição">
            <el-input v-model="formIdentificacao.description" :rows="2" type="textarea"/>
        </el-form-item>
    </el-form>
    <el-form label-position="top" :model="formLocalizacao" :id="step==1?'active':'desactive'" ref="formLocalizacao" :rules="this.rulesLocalizacao">
        <el-row :gutter="20">
            <el-col :span="8">
                <el-form-item label="CEP" prop="cep">
                    <el-input v-model="formLocalizacao.cep" @change="this.findLocation(formLocalizacao.cep)"/>
                </el-form-item>
            </el-col>
            <el-col :span="16">
                <el-form-item label="Endereço" prop="logradouro">
                    <el-input v-model="formLocalizacao.logradouro" />
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="18">
                <el-form-item label="Bairro" prop="bairro">
                    <el-input v-model="formLocalizacao.bairro" />
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="Número" prop="num">
                    <el-input-number v-model="formLocalizacao.num" :min="1" controls-position="right"/>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="18">
                <el-form-item label="Cidade" prop="cidade">
                    <el-input v-model="formLocalizacao.cidade" />
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="UF" prop="uf">
                    <el-select v-model="formLocalizacao.uf" :fit-input-width="true" placeholder="UF">
                        <el-option v-for="(item,index) in this.uf" :key="index" :label="item" :value="item"/>
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="18">
                <el-form-item label="Complemento">
                    <el-input v-model="formLocalizacao.complemento" />
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElSelect,ElOption,ElInputNumber,ElCol,ElRow} from 'element-plus'
import http from '../../api/location'
export default {
    name:"ChamadoForm",
    components:{
        ElForm,
        ElFormItem,
        ElInput,
        ElSelect,
        ElOption,
        ElInputNumber,
        ElCol,
        ElRow
    },
    props:{
        step:Number
    },
    data(){
        return{
            uf:this.listUf(),
            status:status,
            formIdentificacao:{
                name:'',
                description:'',
            },
            formLocalizacao:{
                cep:'',
                bairro:'',
                cidade:'',
                complemento:'',
                logradouro:'',
                uf:'',
                num:null
            },
            rulesIdentificacao:{
                name:{
                    required:true,
                    message:'Insira um nome para o chamado',
                    trigger: 'change'
                }
            },
            rulesLocalizacao:{
                cep:{
                    required:true,
                    message:'Insira um CEP',
                    trigger: 'change'
                },
                bairro:{
                    required:true,
                    message:'Insira o bairro',
                    trigger: 'change'
                },
                cidade:{
                    required:true,
                    message:'Insira a cidade',
                    trigger: 'change'
                },
                logradouro:{
                    required:true,
                    message:'Insira o endereço',
                    trigger: 'change'
                },
                uf:{
                    required:true,
                    message:'Insira a UF',
                    trigger: 'change'
                },
                num:{
                    required:true,
                    message:'Insira o número',
                    trigger: 'change'
                }
            }
        }
    },
    methods:{
        async findLocation(cep){
            const req = await http.get("cep/v1/"+cep)
            if(req.status==200){
                this.formLocalizacao.bairro = req.data.neighborhood
                this.formLocalizacao.cidade = req.data.city
                this.formLocalizacao.uf = req.data.state
                this.formLocalizacao.logradouro = req.data.street
            }
        },
        async listUf(){
            const req = await http.get("ibge/uf/v1")
            const list = []
            if(req.status==200){
                req.data.forEach(uf=>{
                    list.push(uf.sigla)
                })
            }
            this.uf = list
        },
        verifyIdentificacao(){
            this.$refs.formIdentificacao.validate()
            .then(()=>{
                this.$emit('changeForm',1)
            })
        },
        verifyLocalizacao(){
            this.$refs.formLocalizacao.validate()
            .then(()=>{
                this.$emit('submit',{indentificacao:this.formIdentificacao,localizacao:this.formLocalizacao})
            })
        }
    },
    created(){
        this.listUf()
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
</style>