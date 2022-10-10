<template>  
  <BasePage>
    <Title title="Usuários" subtitle="Listagem de usuários, são eles: Administrador > Suporte > Cliente.">
      <ElButton type="primary" @click="this.newModal = true">
        Cadastrar
      </ElButton>
    </Title>
    <TableCollapse :index="{name:'Nome',email:'E-mail',roles:'Nível'}" :tableData="this.tableData">
      <template v-slot="{data}">
        <div class="collapseContent">
          <div>
            <h5>CPF/ CNPJ</h5>
            <p>{{data.cpf}}</p>
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
  <el-dialog v-model="this.newModal" title="Cadastrar Usuário" width="40%" :before-close="handleClose">
    <UsuarioForm ref="usuarioForm" @submit="(form)=>{newUsuario(form)}"/>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="this.newModal = false">Cancelar</el-button>
        <el-button type="primary" @click="this.$refs.usuarioForm.verifyForm()">Cadastrar</el-button>
      </span>
    </template>
  </el-dialog>
  <!--Editar-->
  <el-dialog v-model="this.editModal" :title="'Editar Usuário '+this.selected.name" width="40%" :before-close="handleClose">
    <el-form label-position="top" :model="this.editForm" ref="editForm">
      <el-form-item label="Nome">
        <el-input v-model="this.editForm.name" />
      </el-form-item>
      <el-form-item label="E-mail">
        <el-input v-model="this.editForm.email"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="this.editModal = false">Cancelar</el-button>
        <el-button type="primary" @click="updateUsuario()">Salvar</el-button>
      </span>
    </template>
  </el-dialog>
  <!--Excluir-->
  <el-dialog v-model="this.deleteModal" title="Excluir Usuário" width="40%" align-center>
    <h3>Tem certeza que deseja excluir o usuário {{this.selected.name}}?</h3>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="this.deleteModal = false">Cancelar</el-button>
        <el-button type="primary" @click="deleteUsuario()">Excluir</el-button>
      </span>
    </template>
    </el-dialog>
</template>
<script>
import BasePage from '../components/layout/BasePage.vue'
import Title from '../components/content/Title.vue';
import UsuarioForm from '../components/form/UsuarioForm.vue'
import { ElIcon, ElButton, ElTooltip, ElForm, ElFormItem, ElInput, ElDialog } from 'element-plus';
import TableCollapse from '../components/TableCollapse.vue';
export default{
  name:"Usuarios",
  components:{
    BasePage,
    Title,
    UsuarioForm,
    ElDialog,
    ElButton,
    TableCollapse,
    ElTooltip,
    ElIcon,
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
      deleteModal:false,
      selected:{
        name:null
      },
      editForm:{
        name:null,
        email:null,
      }
    }
  },
  methods:{
    async newUsuario(form){
      const data = {
        name:form.name,
        email:form.email,
        document:form.cpf,
        roles:[
          this.$store.getters.getRoleById(form.roles)
        ]
      }
      await this.$store.dispatch("createUser",data)
      this.tableData = this.$store.getters.getAllUsuarios
      this.newModal = false
      this.$refs.usuarioForm.form = {
        name:null,
        email:null,
        document:null,
        roles:null
      }
    },

    async updateUsuario(){
      const data = {
        id:this.selected.id,
        name:this.editForm.name,
        email:this.editForm.email
      }
      await this.$store.dispatch("updateUsuario",data)
      await this.$store.dispatch("listUsuarios")
      this.tableData = this.$store.getters.getAllUsuarios
      this.editModal = false
    },

    async deleteUsuario(){
      await this.$store.dispatch("deleteUsuario",this.selected.id)
      await this.$store.dispatch("listUsuarios")
      this.tableData = this.$store.getters.getAllUsuarios
      this.deleteModal = false
    },
    openEditModal(data){
      this.selected = data
      this.editForm = {
        name:this.selected.name,
        email:this.selected.email,
      }
      this.editModal = true
    },
    openDeleteModal(data){
      this.selected = data
      this.deleteModal = true
    },
  },
  async created(){
    await this.$store.dispatch("listUsuarios")
    this.tableData = this.$store.getters.getAllUsuarios
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