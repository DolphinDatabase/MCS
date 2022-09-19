<template>
    <div id="page">
        <SideBar/>
        <main-card>
            <MainHeader/>
            <div id="detalhes">
                <header>
                    <div>
                        <span class="tittle">
                            <h2>{{chamado.name}}</h2>
                            <el-tag :type="status[chamado.status].type" effect="plain" round>{{status[chamado.status].label}}</el-tag>
                        </span>
                        <p>{{chamado.user.name}}</p>
                        <p>{{chamado.location.logradouro}}, Nº {{chamado.location.num}}, {{chamado.location.bairro}}</p>
                    </div>
                    <div>
                        <p>{{dateDifference(chamado.date)}} dia(s) atras</p>
                        <el-button v-if="(this.auth.roles[0].id == 2 && this.chamado.status == 'OPEN')" @click="this.orcamentoModal = true" type="primary">Gerar Orçamento</el-button>
                    </div>
                </header>
                <hr/>
                <div id="problemas">
                    <header>
                        <h2>Problemas encontrados</h2>
                        <el-icon v-if="this.auth.roles[0].id == 2" class="icon" style="" color="#000" @click="this.modal = true" :size="18">
                            <Plus />
                        </el-icon>
                    </header>
                    <ProblemaCard ref="problemaCard"/>
                </div>
            </div>
        </main-card>
    </div>
    <el-dialog v-model="this.modal" title="Novo Problema" width="40%" @close='closeModal' align-center>
      <ProblemaForm ref="problemaForm"/>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeModal">Cancelar</el-button>
          <el-button type="primary" @click="submitProblem()">Cadastrar</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="this.orcamentoModal" title="Gerar Orçamento" width="40%" align-center>
      <h3>Encaminhar o chamado para análise ?</h3>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="this.orcamentoModal = false">Cancelar</el-button>
          <el-button type="primary" @click="gerarOrcamento()">OK</el-button>
        </span>
      </template>
    </el-dialog>
</template>
<script>
import MainHeader from '../components/MainHeader.vue'
import MainCard from '../components/MainCard.vue'
import SideBar from '../components/SideBar.vue'
import {ElButton,ElTag,ElDialog} from 'element-plus'
import ProblemaCard from '../components/ProblemaCard.vue'
import ProblemaForm from '../components/ProblemaForm.vue'
import status from '../utils/status'
import dateDifference from '../utils/dateDifference'
export default {
    name:"ChamadoDetail",
    components:{
        SideBar,
        MainCard,
        MainHeader,
        ElButton,
        ElTag,
        ProblemaCard,
        ElDialog,
        ProblemaForm
    },
    data(){
        return{
            chamado:this.$store.getters.getChamadoByid(this.$route.params.id),
            modal:false,
            status:status,
            auth:this.$store.getters.getAuth,
            orcamentoModal:false
        }
    },
    methods:{
        closeModal(){
            this.$refs.problemaForm.displayForm = false
            this.$refs.problemaForm.form.solutions = [{description:''}]
            this.modal = false
        },
        async submitProblem(){
            var problema = {}
            if(this.$refs.problemaForm.problema == null){
                problema = this.$refs.problemaForm.form
            }else{
                problema = {id:this.$refs.problemaForm.problema}
            }
            await this.$store.dispatch("addProblema",{idChamado:this.chamado.id,problema:problema})
            await this.$store.dispatch("listChamado")
            this.$refs.problemaCard.problemas = this.$store.getters.getChamadoByid(this.$route.params.id).problems
            this.closeModal()
        },
        dateDifference:dateDifference,
        async gerarOrcamento(){
            this.chamado.status = "IN_PROGRESS"
            await this.$store.dispatch("updateChamado",this.chamado)
            this.orcamentoModal = false
        }
    },
    mounted(){
        this.$refs.problemaCard.problemas = this.chamado.problems
    }
}
</script>
<style scoped>
    #page{
        height: 100%;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    #detalhes{
        margin-left: 80px;
    }

    #detalhes header{
        display: grid;
        grid-template-columns: 1fr 1fr;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 10px;
    }

     #problemas header{
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 15px 15px 15px 0;
    }

    #problemas header h2{
        color: #000;
        font-size: 20px;
    }

    #problemas header button{
        cursor: pointer;
        color: #000;
        border: 1px solid #000;
        background-color: #fff;
        padding: 3px 3px 0 2px;
        border-radius: 8px;
    }

    .tittle{
        display: flex;
    }

    .tittle h2{
        font-size: 20px;  
        margin-right: 20px;
    }

    #detalhes header div{
        font-size: 14px;        
    }

    #detalhes header div:last-child{
        display: flex;
        height: 100%;
        align-items: end;
        justify-content: space-between;
        flex-direction: column;
    }

    .el-icon{
    cursor: pointer;
    color: #000;
    transition: 0.4s;
  }

  .el-icon:hover{
    color: #0815A3;
    transition: 0.4s;
  }

</style>