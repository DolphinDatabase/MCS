<template>
    <div id="mapeamento" >
        <section id="panel" v-if="this.$store.getters.getAuth.role=='ROLE_ADM' || this.$store.getters.getAuth.role=='ROLE_SUP'">
            <h1>Painel de Controle</h1>
            <div class="box">
                <h5>Criar Silhueta</h5>
                <p>Identificação:</p>
                <el-input placeholder="Digite uma Identificação" v-model="this.uploadName"/>
                <p>Imagem da Silhueta:</p>
                <el-upload drag ref="image" :before-upload="(file)=>handleUpload(file)">
                    <div class="el-upload__text">
                    Drop file here or <em>click to upload</em>
                    </div>
                </el-upload>
            </div>
            <div class="box">
                <h5>Adicionar Mapeamento</h5>
                <p>Silhuetas Conhecidas:</p>
                <el-select v-model="this.mapping" placeholder="Selecione uma Silhueta" style="margin-bottom:1rem">
                    <el-option v-for="item in this.mapeamentos" :label="item.name" :key="item.id" :value="item.id"/>
                </el-select>
                <div class="groupButton">
                    <ElButton type="danger" @click="this.addMapping(this.mapping)">
                        Criar Mapa
                    </ElButton>
                </div>
            </div>
        </section>
        <section id="anomalia" :style="(this.$store.getters.getAuth.role=='ROLE_CLT')?'width:100%':''">
            <h1>Mapeamentos do Chamado</h1>
            <el-collapse accordion class="mapas">
                <el-collapse-item v-for="item in this.mappings" :key="item.id" :name="item.id">
                    <template #title>
                        <section class="collapseTitle">
                            <span>{{item.name}}</span>
                            <span @click="this.$emit('openAnomalia',item.id)">Mais Detalhes</span>
                        </section>
                    </template>
                </el-collapse-item>
            </el-collapse>
            <div class="groupButton"> 
                <el-button @click="this.$emit('cancel')">Fechar</el-button>
            </div>
        </section>
    </div>
</template>
<script>
import {ElButton, ElInput, ElSelect, ElUpload, ElIcon, ElOption, ElCollapse,ElCollapseItem} from 'element-plus'
import { UploadFilled } from '@element-plus/icons-vue'
import {RouterLink} from 'vue-router'
import storage from '../../firebase/storage'
import {getDownloadURL} from 'firebase/storage'
import TableCollapse from '../TableCollapse.vue'

export default {
    name:"Mapeamento",
    components:{
        ElButton,
        RouterLink,
        ElInput,
        ElSelect,
        ElUpload,
        ElIcon,
        TableCollapse,
        UploadFilled,
        ElOption,
        ElCollapse,
        ElCollapseItem
    },
    props:{
        data:Array
    },
    data(){
        return{
            image:null,
            uploadName:'',
            mapeamentos:[],
            mappings:[],
            mapping:null
        }
    },
    methods:{
        UploadFile(){
            this.$refs.image.submit()
        },
        async handleUpload(file){
            const task = await storage.upload(this.uploadName,file)
            const data = {
                name:this.uploadName,
                link:await getDownloadURL(task.ref)
            }
            const res = await this.$store.dispatch("createMapping",data)
            this.mapeamentos = this.$store.getters.getMapeamento
            this.addMapping(res.id)
        },
        clearinfo(){
            this.mapping = ''
            this.uploadName = ''
            this.$refs.image.clearFiles()
        },
        async addMapping(id){
            const data = {
                solicitation:{
                    id:this.$route.params.id
                },
                mapping:this.$store.getters.getMapeamentoById(id)
            }
            const nMapping = await this.$store.dispatch("linkMapeamento",data)
            this.mappings.push({
                id:nMapping.id,
                name:nMapping.mapping.name
            })
            this.clearinfo()
        }
    },
    async created(){
        this.data.forEach(m=>{
            this.mappings.push({
                id:m.id,
                name:m.mapping.name
            })
        })
        await this.$store.dispatch("listMapeamentos")
        this.mapeamentos = this.$store.getters.getMapeamento
    }
}
</script>
<style scoped>
    #anomalia{
        display: flex;
        flex-direction: column;
        width: 75%;
        height: 100%;
        padding: 2rem;
    }

    #anomalia h1{
        margin-bottom: 1.5rem;
    }

    .mapas{
        height: 25rem;
        overflow: auto;
        margin-bottom: 1rem;
    }

    .collapseTitle{
        width:50%;
        display: flex;
        justify-content: space-between;
    }

    .collapseTitle span:last-child{
        cursor: pointer;
        color: var(--color-light);
    }

    .collapseTitle span:last-child:hover{
        color: var(--color-primary-variant);
    }


</style>