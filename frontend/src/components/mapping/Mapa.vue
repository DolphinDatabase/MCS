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
            <el-form v-if="this.showForm" :inline="true" :model="this.sizeForm" size="small">
                <el-form-item label="Largura(mm)" :autofocus="this.sizeForm">
                    <el-input ref="w" @keydown="updateTootip" v-model="this.sizeForm.w" type="number"/>
                </el-form-item>
                <el-form-item label="Altura(mm)">
                    <el-input @keydown="updateTootip" v-model="this.sizeForm.h" type="number"/>
                </el-form-item>
                <el-form-item label="Profundidade(mm)">
                    <el-input @keydown="updateTootip" v-model="this.sizeForm.d" type="number"/>
                </el-form-item>
            </el-form>
            <div v-else style="height:42px"></div>
            <div id="mapa">
                <v-stage ref="stage" :config="stageSize" @click="draw">
                    <v-layer ref="layer">
                       <v-image :config="{image: image}"/>
                       <v-circle v-for="l in this.layers['isolado']" @mouseout="hide" @mousemove="e=>show(e,l)" :key="l.x+l.y" :config="l"/>
                       <v-rect v-for="l in this.layers['agrupado']" @mouseout="hide" @mousemove="e=>show(e,l)" :key="l.x+l.y" :config="l"/>
                       <v-rect v-for="l in this.layers['alongado']" @mouseout="hide" @mousemove="e=>show(e,l)" :key="l.x+l.y" :config="l"/>
                       <v-text :config="tooltip"/>
                    </v-layer>
                <v-layer ref="dragLayer"></v-layer>
                </v-stage>
            </div>
            
            <div class="groupButton"> 
                <el-button type="success" @click="downloadImg()">Exportar</el-button>
                <el-button v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" @click="clearLayers()">Limpar</el-button>
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
            edit:null,
            showForm:false,
            sizeForm:{
                w:'',
                h:'',
                d:''
            },
            stageSize: {
                width: 910,
                height: 400
            },
            image:null,
            layers:{isolado:[],agrupado:[],alongado:[]},
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
            },
            tooltip:{
                x:null,
                y:null,
                text: '',
                fontFamily: 'Arial',
                fontSize: 12,
                padding: 5,
                textFill: 'white',
                fill: 'black',
                alpha: 0.75,
                visible: false,
            }
        }
    },
    methods: {
        clearLayers(){
            this.layers = {isolado:[],agrupado:[],alongado:[]}
            this.$store.commit("clearLayers",{
                chd:this.$route.params.id,
                mapchd:this.data.id
            })
        },
        updateTootip(){
            this.edit.info.w = this.sizeForm.w
            this.edit.info.h = this.sizeForm.h
            this.edit.info.d = this.sizeForm.d
            this.tooltip.text = `${(this.edit.info.w!=''&&this.edit.info.w!=null)?this.edit.info.w:'0'}x${(this.edit.info.h!=''&&this.edit.info.h!=null)?this.edit.info.h:'0'}x${(this.edit.info.d!=''&&this.edit.info.d!=null)?this.edit.info.d:'0'} mm`
        },
        hide(){
            this.tooltip.visible=false
            this.showForm = false
            if(this.edit!=null){
                this.layers[this.edit.type][this.layers[this.edit.type].indexOf(this.edit)]=this.edit
            }
            this.sizeForm.w = ''
            this.sizeForm.h = ''
            this.sizeForm.d = ''
        },
        show(event,layer){
            this.$nextTick(() => this.$refs.w.focus());
            const stage = event.target.getStage();
            const pos = stage.getPointerPosition();
            this.tooltip.x= pos.x + 5
            this.tooltip.y= pos.y + 5
            this.edit = layer
            if(this.$store.getters.getAuth.role!='ROLE_CLT'){
                this.showForm = true
            }
            if(layer.info.w!=''&&layer.info.w!=null){
                this.sizeForm.w = layer.info.w
            }
            if(layer.info.h!=''&&layer.info.h!=null){
                this.sizeForm.h = layer.info.h
            }
            if(layer.info.d!=''&&layer.info.d!=null){
                this.sizeForm.d = layer.info.d
            }
            this.updateTootip()
            this.tooltip.visible = true        
        },
        downloadImg(){
            var stage = this.$refs.stage.getNode()
            const el = document.createElement("a")
            el.setAttribute("href",stage.toDataURL())
            el.setAttribute("download",this.data.mapping.name)
            el.click()
        },
        draw(evt){
            const stage = evt.target.getStage();
            const pos = stage.getPointerPosition();
            const nl = {
                x:pos.x,
                y:pos.y,
                height: this.tamanho,
                width: (this.forma=="alongado")?(this.tamanho*3):this.tamanho,
                fill:this.color,
                type:this.forma,
                info:{
                    w:null,
                    h:null,
                    d:null
                }
            }
            if(this.forma=="isolado"){
                nl['radius']=this.tamanho/2
            }
            this.layers[this.forma].push(nl)
        },
        saveLayers(){
            const data = []
            Object.keys(this.layers).forEach(t=>{
                this.layers[t].forEach(nl=>{
                    data.push({
                        x:nl.x,
                        y:nl.y,
                        color:nl.fill,
                        size:nl.height,
                        type:nl.type,
                        width:(nl.info.w!=''&&nl.info.w!=null)?parseFloat(nl.info.w):0,
                        height:(nl.info.h!=''&&nl.info.h!=null)?parseFloat(nl.info.h):0,
                        depth:(nl.info.d!=''&&nl.info.d!=null)?parseFloat(nl.info.d):0,
                    })
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
        image.crossOrigin = 'anonymous'
        image.onload = () => {
            this.image = image;
            this.image.height = 400
            this.image.width = 910
        };
        if(this.data.layers!=null){
            this.data.layers.forEach(l=>{
                this.layers[l.type].push({
                    id:l.id,
                    x:l.x,
                    y:l.y,
                    height: l.size,
                    width: (l.type=="alongado")?(l.size*3):l.size,
                    fill:l.color,
                    type:l.type,
                    info:{
                        w:l.width,
                        h:l.height,
                        d:l.depth
                    }
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