<template>
    <div id="page">
        <SideBar/>
        <main-card>
            <MainHeader/>
            <section id="listagem">
                <header>
                    <h2>Chamados</h2>
                    <el-icon v-if="this.auth.roles[0].id==3" class="icon" style="" color="#000" @click="this.modal = true" :size="18">
                      <Plus />
                    </el-icon>
                </header>
                <ChamadoCard ref="chamadoCard" :auth="this.auth"/>    
            </section>
        </main-card>
    </div>  
    <el-dialog v-model="this.modal" title="Novo Chamado" width="40%" @close='closeModal' align-center>
      <ChamadoForm ref="chamadoForm" :step="this.formStep"/>
      <template #footer>
        <span v-if="this.formStep == 0" class="dialog-footer">
          <el-button @click="closeModal">Cancelar</el-button>
          <el-button type="primary" @click="this.formStep = 1">Próximo</el-button>
        </span>
        <span v-else class="dialog-footer">
          <el-button @click="closeModal">Cancelar</el-button>
          <el-button @click="this.formStep = 0">Voltar</el-button>
          <el-button type="primary" @click="submitForm()">Cadastrar</el-button>
        </span>
      </template>
    </el-dialog>
</template>
<script>
import MainHeader from '../components/MainHeader.vue'
import MainCard from '../components/MainCard.vue'
import SideBar from '../components/SideBar.vue'
import ChamadoCard from '../components/ChamadoCard.vue'
import ChamadoForm from '../components/ChamadoForm.vue'
import {ElIcon,ElDialog, ElButton} from 'element-plus'

export default {
    name:"Chamados",
    components:{
      MainCard,
      SideBar,
      ChamadoCard,
      MainHeader,
      ElIcon,
      ElDialog,
      ElButton,
      ChamadoForm
    },
    data(){
      return{
        modal:false,
        formStep:0,
        auth:this.$store.getters.getAuth
      }
    },
    methods:{
      async submitForm(){
        const data = this.$refs.chamadoForm.form
        data.user.id = this.auth.id
        data.status = "OPEN"
        await this.$store.dispatch("addChamado",data)
        await this.$store.dispatch("listChamado")
        this.$refs.chamadoCard.dataCard = this.$store.getters.getClienteChamados(this.auth.id)
        this.closeModal()
      },
      closeModal(){
        this.formStep = 0;
        this.$refs.chamadoForm.form = {
          name:'',
          description:'',
          status:'',
          location:{
            cep:'',
            bairro:'',
            cidade:'',
            complemento:'',
            logradouro:'',
            uf:'',
            num:null
          },
          user:{
            id: null
          }
        }
        this.modal = false;
      },
    }
}
</script>
<style>
  #page{
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  #listagem{
    margin-left: 80px;
  }

  #listagem header{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 15px 15px 0;
  }

  #listagem header h2{
    color: #0815A3;
    font-size: 30px;
  }

  /* #listagem header button{
    color: #000;
    border: 1px solid #000;
    background-color: #fff;
    padding: 3px 3px 0 2px;
    border-radius: 8px;
  } */
</style>