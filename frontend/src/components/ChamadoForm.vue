<template>
    <el-form label-position="top" :model="form">
        <div :id="step==0?'active':'desactive'">
            <el-form-item label="Nome">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="Descrição">
                <el-input v-model="form.description" :rows="2" type="textarea"/>
            </el-form-item>
        </div>
        <div :id="step==1?'active':'desactive'">
            <el-form-item label="CEP">
                <el-input v-model="form.location.cep" @change="this.findLocation(form.location.cep)"/>
            </el-form-item>
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="Endereço">
                        <el-input v-model="form.location.logradouro" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="Complemento">
                        <el-input v-model="form.location.complemento" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="Bairro">
                        <el-input v-model="form.location.bairro" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="Cidade">
                        <el-input v-model="form.location.cidade" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="Número">
                <el-input-number v-model="form.location.num" :min="1" controls-position="right"/>
            </el-form-item>
            <el-form-item label="Estado">
                <el-select v-model="form.location.uf" :fit-input-width="true" placeholder="UF" size="large">
                    <el-option v-for="(item,index) in this.uf" :key="index" :label="item" :value="item"/>
                </el-select>
            </el-form-item>
        </div>
    </el-form>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElSelect,ElOption,ElInputNumber,ElCol,ElRow} from 'element-plus'
import http from '../api/location'
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
            form:{
                name:'',
                description:'',
                status:'',
                location:{
                    cep:'',
                    bairro:'',
                    cidade:'',
                    complemento:'',
                    logradouro:'',
                    uf:'',
                    num:null
                },
                user:{
                    id: null
                }
            }
        }
    },
    methods:{
        async findLocation(cep){
            const req = await http.get("cep/v1/"+cep)
            if(req.status==200){
                this.form.location.bairro = req.data.neighborhood
                this.form.location.cidade = req.data.city
                this.form.location.uf = req.data.state
                this.form.location.logradouro = req.data.street
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