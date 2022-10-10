<template>
    <BasePage>
        <Title title="Falhas e Soluções" subtitle="As falhas e soluções cadastradas serão apresentadas na tela que adiciona falhas em chamados.">
            <ElButton v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" type="primary" @click="this.newModal = true">
                Adicionar
            </ElButton>
        </Title>
        <TableCollapse :index="{name:'Falha',num:'Número de soluções',nivel:'Prioridade'}" :tableData="this.tableData">
      <template v-slot="{data}">
        <div class="collapseContent">
            <section style="margin-bottom:1rem">
                <h5>Descrição</h5>
                <p>{{(data.description==null||data.description=='')?'-':data.description}}</p>
            </section>
            <div style="display:flex;justify-content: space-between;;width:100%">
                <section>
                    <h5>Soluções</h5>
                    <ul>
                        <li v-for="solucao in data.solutions" :key="solucao.id">{{solucao.description}}</li>
                    </ul>
                </section>
                <div>
                    <ElTooltip content="Excluir" >
                        <ElButton link @click="openDeleteModal(data)">
                            <ElIcon :size="20" style="color: var(--color-red)">
                                <DeleteFilled />
                            </ElIcon>
                        </ElButton>
                    </ElTooltip>
                </div>
            </div>
        </div>
      </template>
    </TableCollapse>
    </BasePage>
    <!--Adicionar-->
    <el-dialog v-model="this.newModal" title="Cadastrar Falha e Solução" width="40%" :before-close="handleClose">
        <FalhasForm ref="falhasForm" @submit="(form)=>{newFalhas(form)}"/>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.newModal = false">Cancelar</el-button>
                <el-button type="primary" @click="this.$refs.falhasForm.verifyForm()">Cadatrar</el-button>
            </span>
        </template>
    </el-dialog>
    <!--Excluir-->
    <el-dialog v-model="this.deleteModal" title="Excluir Falha" width="40%" align-center>
        <h3>Tem certeza que deseja excluir a Falha {{this.selected.name}}?</h3>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.deleteModal = false">Cancelar</el-button>
                <el-button type="primary" @click="deleteFalhas()">Excluir</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script>
import BasePage from '../components/layout/BasePage.vue';
import Title from '../components/content/Title.vue';
import Table from '../components/Table.vue';
import TableCollapse from '../components/TableCollapse.vue';
import FalhasForm from '../components/form/FalhasForm.vue';
export default{
    name:"Falhas",
    components:{
        BasePage,
        Title,
        Table,
        TableCollapse,
        FalhasForm
    },
    data(){
        return{
            newModal:false,
            deleteModal:false,
            tableData:[],
            selected:null
        }
    },
    methods:{
        async newFalhas(form){
            await this.$store.dispatch("createProblem",form)
            const data = this.$store.getters.getAllProblemas
            console.log(data)
            data.forEach(f => {
                f.num = f.solutions.length
            });
            this.tableData = data
            this.$refs.falhasForm.reset()
            this.newModal = false
        },
        async deleteFalhas(){
            await this.$store.dispatch("deleteProblema",this.selected.id)
            const data = this.$store.getters.getAllProblemas
            data.forEach(f => {
                f.num = f.solutions.length
            });
            this.tableData = data
            this.deleteModal=false
        },
        openDeleteModal(data){
        this.selected = data
        this.deleteModal = true
        },
    },
    
    async created(){
        await this.$store.dispatch("listProblema")
        const data = this.$store.getters.getAllProblemas
        data.forEach(f => {
            f.num = f.solutions.length
        });
        this.tableData = data
    }
}
</script>
<style scoped>
    .collapseContent{
      padding: 2rem;
      display: flex;
      justify-content: space-between;
      flex-direction: column;
    }
</style>