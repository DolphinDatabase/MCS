<template>
    <div id="page">
      <SideBar/>
      <main-card>
        <MainHeader/>
        <div id="content">
          <header>
            <h1 class="title">Orçamentos</h1>
          </header>
          <div class="container">
            <main-table ref="orcamentoTable"/>
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
import MainTable from '../components/TableBudget.vue'
import UsuarioForm from '../components/UsuarioForm.vue'
import {ElButton,ElIcon} from 'element-plus'
import generate from '../utils/randomPass'
export default {
  name:"Orcamento",
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
      this.$refs.orcamentoTable.tableData = this.$store.getters.getAllUsuarios
      this.closeModal()
    }
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

  .title {
    font-family: 'Roxborough CF';
    font-style: normal;
    color: var(--color-primary);
  }

  .container {
    display: flex;
    width: 100%;
    height: 90%;
  }

</style>