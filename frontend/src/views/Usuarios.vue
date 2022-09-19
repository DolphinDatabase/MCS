<template>
    <div id="page">
      <SideBar/>
      <main-card>
        <MainHeader/>
        <div id="content">
          <header>
            <h2>Gerenciar Usuários</h2>
            <el-button @click="this.modal = true" type="success">
              <el-icon :size="12">
                <Plus/>
              </el-icon>
              &nbsp;Novo Usuário
            </el-button>
          </header>
          <div class="container">
            <main-table ref="usuarioTable"/>
          </div>
        </div>
      </main-card>
    </div>
    <el-dialog v-model="this.modal" title="Cadastrar Usuário" @close="this.closeModal()" width="40%" align-center>
      <UsuarioForm ref="usuarioForm"/>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="this.modal = false">Cancelar</el-button>
          <el-button type="primary" @click="submitForm()">Salvar</el-button>
        </span>
      </template>
    </el-dialog>
</template>
<script>
import MainCard from '../components/MainCard.vue'
import SideBar from '../components/SideBar.vue'
import MainHeader from '../components/MainHeader.vue'
import MainTable from '../components/TableUser.vue'
import UsuarioForm from '../components/UsuarioForm.vue'
import {ElButton,ElIcon} from 'element-plus'
import generate from '../utils/randomPass'
export default {
  name:"Usuarios",
  components:{
    MainCard,
    SideBar,
    MainHeader,
    MainTable,
    UsuarioForm,
    ElButton,
    ElIcon
  },
  data(){
    return{
      modal:false
    }
  },
  methods:{
    closeModal(){
      this.$refs.usuarioForm.form = {
        name:'',
        email:'',
        password:'',
        roles:null
      }
      this.modal = false
    },
    async submitForm(){
      const form = this.$refs.usuarioForm.form
      form.roles = [this.$store.getters.getRoleById(form.roles)]
      form.password = generate()
      await this.$store.dispatch("addUsuario",form)
      await this.$store.dispatch("listUsuarios")
      this.$refs.usuarioTable.tableData = this.$store.getters.getAllUsuarios
      this.closeModal()
    }
  },
  created(){
    this.$store.dispatch("listRoles")
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

  header{
    display:flex;
    align-items: center;
    justify-content: space-between;
  }

  #content{
    margin-left: 80px;
  }

  #content header h2{
    color: #0815A3;
    font-size: 30px;
  }

  .container {
    display: flex;
    width: 100%;
    height: 90%;
  }

</style>

