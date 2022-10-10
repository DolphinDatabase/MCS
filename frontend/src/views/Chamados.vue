<template>  
  <BasePage>
    <Title title="Chamados">
      <ElButton v-if="this.$store.getters.getAuth.role=='ROLE_CLT'" type="primary" @click="this.newModal = true">
        Adicionar
      </ElButton>
    </Title>
    <ChamadoList :chamados="this.chamados" @click="(chamado)=>setRelatorio(chamado)"/>
  </BasePage>
  <el-dialog v-model="this.newModal" title="Adicionar Chamado" width="40%" :before-close="handleClose">
    <div>
      <div id="step">
        <span :style="(this.formStep==0)?'color:#0024FF':''">Identificação</span>
        <el-icon :size="16">
          <ArrowRight />
        </el-icon>
        <span :style="(this.formStep==1)?'color:#0024FF':''">Localização</span>
      </div>
      <ChamadoForm ref="chamadoForm" @changeForm="(val)=>this.formStep=val" @submit="(form)=>newChamado(form)" :step="this.formStep"/>
    </div>
    <template #footer>
      <span v-if="this.formStep==0" class="dialog-footer">
        <el-button @click="this.newModal = false">Cancelar</el-button>
        <el-button type="primary" @click="this.$refs.chamadoForm.verifyIdentificacao()">Próximo</el-button>
      </span>
      <span v-if="this.formStep==1" class="dialog-footer">
        <el-button @click="this.newModal = false">Cancelar</el-button>
        <el-button @click="this.formStep=0">Voltar</el-button>
        <el-button type="primary" @click="this.$refs.chamadoForm.verifyLocalizacao()">Salvar</el-button>
      </span>
    </template>
  </el-dialog>
  <RelatorioModal v-if="this.relatorioModal" :data="relatorio" @close="updatePage()"/>
</template>
<script>
  import BasePage from '../components/layout/BasePage.vue'
  import Title from '../components/content/Title.vue'
  import ChamadoList from '../components/content/ChamadoList.vue'
  import ChamadoForm from '../components/form/ChamadoForm.vue'
  import {ElButton,ElIcon} from 'element-plus'
  import {ArrowRight} from '@element-plus/icons-vue'
  import RelatorioModal from '../components/RelatorioModal.vue'
  export default{
    name:"Chamados",
    components:{
      BasePage,
      Title,
      ChamadoList,
      ElButton,
      ElIcon,
      ArrowRight,
      ChamadoForm,
      RelatorioModal
    },
    data(){
      return{
        chamados:[],
        newModal:false,
        relatorioModal:false,
        relatorio:{},
        formStep:0
      }
    },
    methods:{
      async newChamado(form){
        const data = {
          name:form.indentificacao.name,
          description:form.indentificacao.description,
          status:'OPEN',
          location:form.localizacao
        }
        await this.$store.dispatch("addChamado",data)
        this.chamados = this.$store.getters.getAllChamados
        this.newModal = false
      },
      setRelatorio(chamado){
        this.relatorio = chamado
        this.relatorioModal = true
      },
      async updatePage(){
        this.updateData()
        this.relatorioModal=false
      },
      async updateData(){
        await this.$store.dispatch("listChamados")  
        this.chamados = this.$store.getters.getAllChamados
      }
    },
    async created(){
      this.updateData()
    }
  }
</script>
<style scoped>
#step{
  background-color: #F3F7FF;
  display: flex;
  gap: 20px;
  justify-content: center;
  padding: 0.8rem;
  font-size: 16px;
  margin-bottom: 20px;
}

#step .el-icon{
  margin: 0 0.4rem;
}
</style>