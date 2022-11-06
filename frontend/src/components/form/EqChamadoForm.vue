<template>
    <el-form label-position="top" ref="eqChamadoForm" :model="this.form" :rules="this.rules">
        <el-row :gutter="20">
            <el-col :span="14">
                <el-form-item label="Equipamentos" prop="modelo">
                    <el-select v-model="form.modelo" :fit-input-width="true" placeholder="Escolha um equipamento" @change="()=>{this.selected = this.materials.find(ch=>ch.num==form.modelo);}">
                        <el-option label="Escolha um equipamento" disabled="false"/>
                        <el-option v-for="item in this.materials" :key="item.num" :label="item.model" :value="item.num"/>
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
    <section v-if="form.modelo!=null">
        <div id="descricao">
            <h2>Descrição</h2>
            <p>{{(this.selected.description!=null||this.selected.description!='')?this.selected.description:'-'}}</p>
        </div>
    </section>
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
            materials:[],
            selected:{
                description: null,
                inventory: null, 
                model: null,
                num: null,
            },
            form:{
                num:null,
                modelo:null,
            },
            rules:{
                num:[
                    { required: true, message: 'Insira a quantidade' },
                    { type: 'number', message: 'Deve ser um número.' },
                ],
                modelo:{
                    required:true,
                    message:'Insira o modelo',
                    trigger: 'change'
                },
            }
        }
    },
    methods:{
        verifyForm(){
            this.$refs.eqChamadoForm.validate()
            .then(()=>{
                this.$emit("submit",this.selected)
            })
        },
        reset(){
            this.form={
                num:null,
                modelo:null,
            }
        }
    },
    async created(){
        await this.$store.dispatch("listMaterials")
        this.materials = this.$store.getters.getAllMaterials

    }
}
</script>
<style scoped>
    #descricao {
        display: flex;
        flex-direction: column;
    }

    #descricao h2{
        font-size: 13px;
    }

    #quantidade{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 10px;
    }

    #quantidade h2{
        font-size: 13px;
    }

    .info{
        display: flex;
        justify-content: space-between;
    }

    .info p{
        margin: 20px;
    }
</style>