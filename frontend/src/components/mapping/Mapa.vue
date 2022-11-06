<template>
    <div id="mapeamento" >
        <section id="panel">
            <div class="backTitle">
                <ElButton @click="this.$emit('changeStep',0)">
                    <el-icon><Back /></el-icon>
                </ElButton>
                <h1>Painel de Controle</h1>
            </div>
            <div v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" class="box">
                <h5>Desenhar</h5>
                <p>Falha:</p>
                <el-select v-model="this.color">
                    <el-option v-for="c in this.colors" :style="'color:'+c.color" :key="c.color" :label="c.name" :value="c.color"/>
                </el-select>
                <p>Tamanho:</p>
                <el-select v-model="this.tamanho">
                    <el-option v-for="(t,i) in this.tamanhos" :key="i" :label="t.name" :value="t.value"/>
                </el-select>
                <p>Forma:</p>
                <el-select v-model="this.forma">
                    <el-option v-for="(f,i) in this.formas" :key="i" :label="f" :value="i"/>
                </el-select>
            </div>
            <div class="box">
                <h5 style="margin-bottom:1.5rem">Legenda</h5>
                <ColorSubtitle :data="this.colors"/>
            </div>
        </section>
        <section id="anomalia">
            <h2>{{this.data.mapping.name}}</h2>
            <div id="mapa">
                <v-stage ref="stage" :config="stageSize" @click="draw">
                    <v-layer ref="layer">
                       <v-image :config="{image: image}"/>
                       <v-circle v-for="l in this.layers['isolado']" :key="l.x+l.y" :config="l"/>
                       <v-rect v-for="l in this.layers['agrupado']" :key="l.x+l.y" :config="l"/>
                       <v-rect v-for="l in this.layers['alongado']" :key="l.x+l.y" :config="l"/>
                    </v-layer>
                <v-layer ref="dragLayer"></v-layer>
                </v-stage>
            </div>
            <div class="groupButton"> 
                <el-button :type="(this.$store.getters.getAuth.role=='ROLE_CLT')?'primary':''" @click="this.$emit('changeStep',0)">Voltar</el-button>
                <el-button v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" type="primary" @click="saveLayers">Salvar</el-button>
            </div>
        </section>
    </div>
</template>
<script>
import {ElButton, ElInput, ElSelect, ElOption} from 'element-plus'
import {RouterLink} from 'vue-router'
import TableCollapse from '../TableCollapse.vue';
import ColorSubtitle from '../subtitle/ColorSubtitle.vue'

export default {
    name:"Mapa",
    components:{
        ElButton,
        RouterLink,
        ElInput,
        ElSelect,
        TableCollapse,
        ElOption,
        ColorSubtitle
    },
    props:{
        data:Object,
        colors:Array
    },
    data(){
        return{
            stageSize: {
                width: 910,
                height: 400
            },
            image:null,
            layers:{isolado:[],agrupado:[],alongado:[]},
            nLayers:[],
            color:'',
            forma:'',
            formas:{
                isolado:"Isolado",agrupado:"Agrupado",alongado:"Alongado"
            },
            tamanho:'',
            tamanhos:{
                small:{
                    name:"Pequeno",
                    value:5
                },medium:{
                    name:"Médio",
                    value:10
                },large:{
                    name:"Grande",
                    value:15
                }
            }
        }
    },
    methods: {
        draw(evt){
            const stage = evt.target.getStage();
            const pos = stage.getPointerPosition();
            const nl = {
                x:pos.x,
                y:pos.y,
                height: this.tamanho,
                width: (this.forma=="alongado")?(this.tamanho*3):this.tamanho,
                fill:this.color,
                type:this.forma
            }
            if(this.forma=="isolado"){
                nl['radius']=this.tamanho/2
            }
            this.layers[this.forma].push(nl)
            this.nLayers.push(nl)
        },
        clearLayers(){
           this.nLayers.forEach(nl=>{
            if(this.layers.find(l=>nl==l)!=null){
                
            }
           })
        },
        saveLayers(){
            const data = []
            this.nLayers.forEach(nl=>{
                data.push({
                    x:nl.x,
                    y:nl.y,
                    color:nl.fill,
                    size:nl.height,
                    type:nl.type
                })
            })
            this.$store.dispatch("addLayers",{
                chd:this.$route.params.id,
                mapchd:this.data.id,
                layers:data
            })
            this.$emit('changeStep',0)
        }
    },
    created(){
        const image = new window.Image();
        image.src = this.data.mapping.link;
        image.onload = () => {
            this.image = image;
            this.image.height = 400
            this.image.width = 910
        };
        if(this.data.layers!=null){
            console.log(this.data.layers)
            this.data.layers.forEach(l=>{
                this.layers[l.type].push({
                    x:l.x,
                    y:l.y,
                    height: l.size,
                    width: (l.type=="alongado")?(l.size*3):l.size,
                    fill:l.color,
                    type:l.type
                })
                if(l.type=="isolado"){
                    this.layers['isolado']['radius']=this.size/2
                }
            })
        }
    }
}
</script>
<style scoped>

    .backTitle{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        margin-bottom: 1.5rem;
    }

    .backTitle h1{
        margin: 0 !important
    }

    #anomalia{
        display: flex;
        flex-direction: column;
        width: 75%;
        height: 100%;
        padding: 2rem;
    }

    #anomalia h2{
        margin-bottom: 1.5rem
    }

    #mapa{
        margin-bottom: 1rem;
    }
</style>