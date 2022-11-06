<template>
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
            <div id="relatorio" :class="this.statusData[this.chamado.status].class">
                <section id="info">
                    <h1>Informações do Chamado</h1>
                    <div>
                        <h5>Situação:</h5>
                        <el-tag id="statusTag" :type="this.statusData[this.chamado.status].type" size="large">{{this.statusData[this.chamado.status].label}}</el-tag>
                    </div>
                    <div>
                        <h5>Criado em:</h5>
                        <p>30/09/2022 às 20:00</p>
                    </div>
                    <section>
                        <div>
                            <h5>CEP:</h5>
                            <p>{{this.chamado.location.cep}}</p>
                        </div>
                        <div>
                            <h5>Número:</h5>
                            <p>{{this.chamado.location.num}}</p>
                        </div>
                    </section>
                    <div>
                        <h5>Endereço:</h5>
                        <p>{{this.chamado.location.logradouro}}</p>
                    </div>
                    <div>
                        <h5>Complemento:</h5>
                        <p>{{(this.chamado.location.complemento!=null)?this.chamado.location.complemento:'-'}}</p>
                    </div>
                    <div>
                        <h5>Bairro:</h5>
                        <p>{{this.chamado.location.bairro}}</p>
                    </div>
                    <div>
                        <h5>Cidade:</h5>
                        <p>{{this.chamado.location.cidade}}</p>
                    </div>
                    <div>
                        <h5>Estado:</h5>
                        <p>{{this.chamado.location.uf}}</p>
                    </div>
                </section>
                <section id="details">
                    <h1>Expansões do chamado</h1>
                    <header>
                        <div>
                            <h5>Identificação:</h5>
                            <h2>{{'#'+this.chamado.id+' '+this.chamado.name}}</h2>
                        </div>
                        <div>
                            <h5>Cliente:</h5>
                            <p>{{this.chamado.user.name}}</p>
                        </div>
                    </header>
                    <div id="description">
                        <h5>Descrição</h5>
                        <p>{{(this.chamado.description!=null&&this.chamado.description!='')?this.chamado.description:'-'}}</p>
                    </div>
                    <section id="groupCollapse">
                        <el-collapse accordion>
                            <el-collapse-item name="1">
                                <template #title>
                                    <span style="color:#F56C6C">
                                        Falhas e problemas
                                    </span>
                                </template>
                                <div v-if="this.chamado.problems!=null&&this.chamado.problems.length>0" id="collapseFalha" class="collapseContent">
                                    <span>Número de falhas encontradas: {{this.chamado.problems.length}}</span>
                                    <router-link :to="'/chamado/'+this.chamado.id">Mais detalhes</router-link>
                                </div>
                                <div v-else id="collapseFalha" class="collapseContent">
                                    <span>Nenhuma falha encontrada</span>
                                    <router-link v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" :to="'/chamado/'+this.chamado.id">Adicionar</router-link>
                                </div>
                            </el-collapse-item>
                            <el-collapse-item name="2">
                                <template #title>
                                    <span style="color:#E6A23C">
                                        Equipamentos
                                    </span>
                                </template>
                                <div id="collapseMaterial" class="collapseContent">
                                    <div v-if="this.$store.getters.getAuth.role=='ROLE_SUP'">
                                    <div class="add" v-if="this.chamado.status!='FINISHED'">
                                        <el-button type="warning" @click="this.equipModal = true">
                                            Adicionar
                                        </el-button>
                                    </div>
                                    <div id="item" v-if="this.chamado.materials!=null&&this.chamado.materials.length>0">
                                        <ul>
                                            <li v-for="item in this.chamado.materials" :key="item.num">{{item.model}}</li>
                                        </ul> 
                                    </div>
                                    <div v-else id="warning">
                                        <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                        <h2>Equipamentos pendentes, adicione-os.</h2>
                                    </div>
                                    </div>
                                    <div v-else id="warning">
                                        <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                        <h2>Equipamentos pendentes, peça para um suporte adicioná-los.</h2>
                                    </div>
                                    
                                </div>
                            </el-collapse-item>
                            <el-collapse-item name="3">
                                <template #title>
                                    <div style="color:#67C23A">
                                        Orçamento
                                    </div>
                                </template>
                                <div id="collapseOrcamento" class="collapseContent">
                                    <div id="orcamento" v-if="this.chamado.budget!=null&&this.chamado.budget!=''">
                                        <h5>Orçamento:</h5>
                                        <p>{{this.chamado.budget.total.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})}}</p>
                                        <div v-if="this.chamado.budget.description!=null&&this.chamado.budget.description!=''">
                                            <h5>Descrição</h5>
                                            <p>{{this.chamado.budget.description}}</p>
                                        </div>
                                    </div>
                                    <div v-else>
                                        <div v-if="this.$store.getters.getAuth.role=='ROLE_ADM'&&this.chamado.status!='FINISHED'">
                                            <div class="add" >
                                                <el-button type="success" @click="this.budgetModal = true">
                                                    Adicionar
                                                </el-button>
                                            </div>
                                            <div id="warning">
                                                <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                                <h2>Orçamento pendente, adicione um valor.</h2>
                                            </div>
                                        </div>
                                        <div v-else>
                                            <div id="warning">
                                                <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                                <h2>Orçamento pendente, peça para um administrador adicionar o valor.</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <el-collapse-item name="4">
                                <template #title>
                                    <div style="color:#409EFF">
                                        Administrativo
                                    </div>
                                </template>
                                <div id="collapseAdministrativo" class="collapseContent">
                                    <div id="administrativo" v-if="(this.chamado.service!=null&&this.chamado.service!='')&&(this.chamado.responsible!=null&&this.chamado.responsible!={})">
                                        <h5>Data para execução do Serviço:</h5>
                                        <p>{{new Date(this.chamado.service).getDate()+"/"+(new Date(this.chamado.service).getMonth()+1)+"/"+new Date(this.chamado.service).getFullYear()}}</p>
                                        <h5>Responsável:</h5>
                                        <p>{{this.chamado.responsible.name}}</p>
                                    </div>
                                    <div v-else>
                                        <div v-if="(this.$store.getters.getAuth.role=='ROLE_ADM' || this.$store.getters.getAuth.role=='ROLE_SUP')&&this.chamado.status!='FINISHED'">
                                            <div class="add" >
                                                <el-button id="adm" type="success" @click="this.admModal = true">
                                                    Adicionar
                                                </el-button>
                                            </div>
                                            <div id="warning">
                                                <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                                <h2>Informações Pendentes, são elas: data e responsável por serviço.</h2>
                                            </div>
                                        </div>
                                        <div v-else>
                                            <div id="warning">
                                                <img src="../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                                                <h2>Informações Pendentes, peça para um administrador preenche-las.</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                    </section> 
                    <div id="groupButton"> 
                        <el-button @click="this.$emit('cancel')">Cancelar</el-button>
                        <el-button v-if="(this.$store.getters.getAuth.role=='ROLE_ADM' || this.$store.getters.getAuth.role=='ROLE_SUP')&&this.chamado.status!='FINISHED'" type="success" @click="this.$emit('finish',this.chamado)">Finalizar</el-button>
                        <el-button type="primary" @click="this.$emit('close')">Salvar</el-button>
                    </div>
                </section>
            </div>
        </div>
      </div>
    </div>
    <!--Adicionar Orçamento-->
    <el-dialog v-model="this.budgetModal" title="Orçamento" width="40%">
        <OrcamentoForm ref="orcamentoForm" @submit="(form)=>addBudget(form)"/>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.budgetModal = false">Cancelar</el-button>
                <el-button type="primary" @click="this.$refs.orcamentoForm.verifyForm()">Salvar</el-button>
            </span>
        </template>
    </el-dialog>
    <!--Adicionar Administrativo-->
    <el-dialog v-model="this.admModal" title="Administrativo" width="40%">
        <AdministrativoForm ref="administrativoForm" @submit="(form)=>addAdministrativo(form)"/>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.admModal = false">Cancelar</el-button>
                <el-button type="primary" @click="this.$refs.administrativoForm.verifyForm()">Salvar</el-button>
            </span>
        </template>
    </el-dialog>
    <!--Adicionar Equipamento-->
    <el-dialog v-model="this.equipModal" title="Adicionar Equipamentos ao Chamado" width="40%" @close="this.$refs.eqChamadoForm.reset()">
        <EqChamadoForm ref="eqChamadoForm" @submit="(form)=>addMaterial(form)"/>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.equipModal = false">Cancelar</el-button>
                <el-button type="primary" @click="this.$refs.eqChamadoForm.verifyForm()">Adicionar</el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script>
import status from '../utils/status'
import {ElTag, ElCollapse, ElCollapseItem,ElButton, ElDialog} from 'element-plus'
import OrcamentoForm from '../components/form/OrcamentoForm.vue';
import EqChamadoForm from './form/EqChamadoForm.vue';
import AdministrativoForm from '../components/form/AdministrativoForm.vue';
import {RouterLink} from 'vue-router'
export default {
    name:"RelatorioModal",
    components:{
        ElTag,
        ElCollapse, 
        ElCollapseItem,
        ElButton,
        ElDialog,
        OrcamentoForm,
        EqChamadoForm,
        AdministrativoForm,
        RouterLink
    },
    props:{
        data:Object
    },
    data(){
        return{
            statusData:status,
            equipModal:false,
            budgetModal:false,
            admModal:false,
            chamado:this.data
        }
    },
    methods:{
        async addBudget(form){
            await this.$store.dispatch("createOrcamento",{form:form,id:this.chamado.id})
            this.chamado.budget = form
            this.budgetModal = false
        },
        async addMaterial(form){
            await this.$store.dispatch("addMaterial",{form:form,chamado:this.chamado})
            this.chamado.materials.push(form)
            this.equipModal = false
        },
        async addAdministrativo(form){
            const d = new Date(form.service)
            const data = {
               service:`${d.getFullYear()}-${(d.getMonth())+1}-${d.getDate()}`,
               responsible:{
                id:form.responsible 
               }
            }
            console.log(data)
            const res = await this.$store.dispatch("addService",{form:data,chamado:this.chamado.id})
            this.chamado.service = res.service
            this.chamado.responsible = res.responsible
            this.admModal = false
        }
    }
}
</script>
<style scoped>
    
    #collapseMaterial{
        padding: 0 1rem;
    }

    #collapseMaterial ul{
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
    }

    #collapseFalha{
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    #groupButton{
        margin: auto 0 0 auto;
    }

    .collapseContent{
        max-height: 6rem;
        overflow: auto;
    }

    #groupCollapse{
        margin-top: 2rem;
    }

    #details{
        padding: 2rem;
        display: flex;
        flex-direction: column;
    }

    #details header{
        margin: 1.5rem 0 1rem 0;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    #details header h2{
        font-size: 22px;
    }

    #description{
        padding: 0 1rem;
    }

    #statusTag{
        margin-left: 40%;
    }

    #relatorio{
        width: 100%;
        height: 100%;
        display: flex;
    }

    #relatorio h1{
        font-size: 18px;
    }

    #relatorio p{
        color: #757575;
    }

    #relatorio p, #relatorio h5{
        font-size: 16px;
    }

    #info{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 25%;
        padding: 2rem;
        font-size: 16px;
        box-shadow: 1px 0px 25px rgba(0, 0, 0, 0.25);
        border-radius: 10px
    }
    
    #info h1{
        margin-bottom: 1.5rem;
    }

    #info div{
        width: 80%;
        margin-bottom: 1rem;
    }

    #info section{
        width: 80%;
        margin-bottom: 1rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    #info section div{
        margin:0
    }

    #details{
        width: 75%;
    }

    #warning {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #warning h2{
        font-size: 15px;
        color: #A5A5A5;
        font-weight: lighter;
    }

    #adm{
        background-color:#409EFF;
    }
    .add {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .open{
        border-left: 10px solid var(--color-red);
    }

    .andamento{
        border-left: 10px solid var(--color-orange);
    }
        
    .finished{
        border-left: 10px solid var(--color-green);
    }

    .modal-mask {
        position: fixed;
        z-index: 12;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        display: table;
        transition: opacity 0.3s ease;
    }

    .modal-wrapper {
        display: table-cell;
        vertical-align: middle;
    }

    .modal-container {
        width: 85%;
        height: 80%;
        margin: 0px auto;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
        transition: all 0.3s ease;
        font-family: Helvetica, Arial, sans-serif;
    }

    .modal-enter-from, .modal-leave-to {
        opacity: 0;
    }

    .modal-enter-active .modal-container,
    .modal-leave-active .modal-container {
        -webkit-transform: scale(1.1);
        transform: scale(1.1);
    }
</style>