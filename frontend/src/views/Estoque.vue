<template>
    <BasePage>
        <Title title="Equipamentos" subtitle="Listagem de equipamentos do estoque.">
            <ElButton v-if="this.$store.getters.getAuth.role!='ROLE_CLT'" type="primary" @click="this.newModal = true">
                Adicionar
            </ElButton>
        </Title>
        <TableCollapse :index="{num:'Núm. de Série', model:'Modelo', inventory:'Disponível'}" :tableData="this.tableData">
            <template v-slot="{data}">
                <div class="collapseContent">
                    <div>
                        <h5>Descrição:</h5>
                        <p>{{data.description}}</p>
                    </div>
                    <div>
                        <ElTooltip content="Editar" >
                            <ElButton link style="color: var(--color-primary-variant)" @click="openEditModal(data)">
                                <ElIcon :size="20">
                                    <Edit/>
                                </ElIcon>
                            </ElButton>
                        </ElTooltip>
                        <ElTooltip content="Excluir" >
                            <ElButton link @click="openDeleteModal(data)">
                                <ElIcon :size="20" style="color: var(--color-red)">
                                    <DeleteFilled />
                                </ElIcon>
                            </ElButton>
                        </ElTooltip>
                    </div>
                </div>
            </template>
        </TableCollapse>
    </BasePage>
    <!--Adicionar-->
    <el-dialog v-model="this.newModal" title="Adicionar Equipamento" width="40%" :before-close="handleClose">
        <EquipamentoForm ref="equipamentoForm"  @submit="(form)=>{newEquipamento(form)}"/>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.newModal = false">Cancelar</el-button>
                <el-button type="primary" @click="this.$refs.equipamentoForm.verifyForm()">Salvar</el-button>
            </span>
        </template>
    </el-dialog>
    <!--Editar-->
    <el-dialog v-model="this.editModal" :title="'Editar Equipamento #'+this.selected.num" width="40%" :before-close="handleClose">
        <el-form label-position="top" :model="this.editForm" ref="editForm">
            <el-form-item label="Modelo do Item">
                <el-input v-model="this.editForm.model" />
            </el-form-item>
            <el-form-item label="Descrição">
                <el-input v-model="this.editForm.description" :rows="2" type="textarea"/>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="this.editModal = false">Cancelar</el-button>
                <el-button type="primary" @click="updateEquipamento()">Salvar</el-button>
            </span>
        </template>
    </el-dialog>
    <!--Excluir-->
    <el-dialog v-model="this.deleteModal" title="Excluir Equipamento" width="40%" align-center>
        <h3>Tem certeza que deseja excluir o equipamento #{{this.selected.num}} ?</h3>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="this.deleteModal = false">Cancelar</el-button>
          <el-button type="primary" @click="deleteEquipamento()">Deletar</el-button>
        </span>
      </template>
    </el-dialog>
</template>

<script>
import BasePage from '../components/layout/BasePage.vue';
import Title from '../components/content/Title.vue';
import TableCollapse from '../components/TableCollapse.vue';
import EquipamentoForm from '../components/form/EquipamentoForm.vue';
import { ElIcon, ElButton, ElTooltip, ElForm, ElFormItem, ElInput, ElDialog } from 'element-plus';
export default{
    name:"Estoque",
    components:{
    BasePage,
    Title,
    TableCollapse,
    EquipamentoForm,
    ElButton,
    ElIcon,
    ElTooltip,
    ElForm,
    ElFormItem, 
    ElInput,
    ElDialog
},
    data(){
        return{
            tableData:[],
            newModal:false,
            editModal:false,
            editForm:null,
            deleteModal:false,
            selected:{
                num: null
            },
            editForm:{
                model:null,
                description:null
            }
        }
    },
    methods:{
        async newEquipamento(form){
            const data = {
                model:form.modelo,
                num:form.num,
                inventory:true,
                description:form.description,
            }
            await this.$store.dispatch("createMaterials",data)
            this.tableData = this.$store.getters.getAllMaterials
            this.newModal = false
            this.$refs.equipamentoForm.form = {
                description: null,
                model: null,
                num: null,
            }
        },
        async updateEquipamento(){
            const data = {
                num:this.selected.num,
                model:this.editForm.model,
                inventory:this.selected.inventory,
                description:this.editForm.description
            }
            await this.$store.dispatch("updateMaterials",data)
            await this.$store.dispatch("listMaterials")
            this.tableData = this.$store.getters.getAllMaterials
            this.editModal = false
        },
        async deleteEquipamento(){
            await this.$store.dispatch("deleteMaterials",this.selected.num)
            await this.$store.dispatch("listMaterials")
            this.tableData = this.$store.getters.getAllMaterials
            this.deleteModal = false
        },
        myEvent(item){
            console.log(item)
        },
        openEditModal(data){
            this.selected = data
            this.editForm = {
                model:this.selected.model,
                description:this.selected.description
            },
            this.editModal = true
        },
        openDeleteModal(data){
            this.selected = data
            this.deleteModal = true
        },
    },
    async created(){
        await this.$store.dispatch("listMaterials")
        this.tableData = this.$store.getters.getAllMaterials
    }
}
</script>

<style scoped>
    .collapseContent{
        padding: 2rem;
        display: flex;
        justify-content: space-between;
    }
</style>