<template>  
  <BasePage>
    <Title title="Falhas e soluções do Chamado">
      <ElButton v-if="this.$store.getters.getAuth.role=='ROLE_SUP'" type="primary" @click="this.newModal = true">
        Adicionar
      </ElButton>
    </Title>
    <TableCollapse :index="{name:'Falha',num:'Número de soluções',nivel:'Prioridade'}" :tableData="this.problemas">
      <template v-slot="{data}">
        <div style="padding:0 2rem">
          <h5>Soluções</h5>
          <ul>
            <li v-for="solucao in data.solutions" :key="solucao.id">{{solucao.description}}</li>
          </ul>
        </div>
        <div style="padding:0 2rem;margin-top:1rem">
          <h5>Dimensões da falha</h5>
          <p>{{data.width}}x{{data.height}}x{{data.depth}}mm</p>
        </div>
      </template>
    </TableCollapse>
  </BasePage>
  <el-dialog v-model="this.newModal" title="Cadastrar Falhas e Soluções" width="40%" :before-close="handleClose">
    <div>
      <div id="step">
        <span :style="(this.formType==0)?'color:#0024FF':''" @click="this.formType=0">Falha Conhecida</span>
        <span :style="(this.formType==1)?'color:#0024FF':''" @click="this.formType=1">Nova Falha</span>
      </div>
      <ProblemForm ref="problemForm" :type="this.formType" @submit="(form)=>handleSubmit(form)"/>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="this.newModal = false">Cancelar</el-button>
        <el-button type="primary" @click="this.$refs.problemForm.verifyForm()">Salvar</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
  import BasePage from '../components/layout/BasePage.vue'
  import Title from '../components/content/Title.vue'
  import TableCollapse from '../components/TableCollapse.vue'
  import ProblemForm from '../components/form/ProblemForm.vue'
  import {ElButton} from 'element-plus'
  export default{
    name:"ChamadoDetail",
    components:{
      BasePage,
      Title,
      ElButton,
      TableCollapse,
      ProblemForm
    },
    data(){
      return{
        problemas:[],
        newModal:false,
        formType:0
      }
    },
    created(){
      console.log(this.$route.params.id)
      const chamado = this.$store.getters.getChamadoByid(this.$route.params.id)
      chamado.problems.forEach(p => {
        this.problemas.push({
          id:p.problem.id,
          name:p.problem.name,
          nivel:p.problem.nivel,
          width: p.width,
          height: p.height,
          depth: p.depth,
          num:(p.problem.solutions.length==0||p.problem.solutions.length==null)?0:p.problem.solutions.length,
          solutions:p.problem.solutions,
        })
      });
      console.log(this.problemas)
    },
    methods:{
      async handleSubmit(form){
        const data = {
          solicitation:{
            id:this.$route.params.id
          },
          problem:{
              id:null
          },
          width:form.width,
          height:form.height,
          depth:form.depth
        }
        if(form.known==null){
          const problemData={
            name:form.name,
            nivel:form.nivel,
            solutions:form.solutions
          }
          const p = await this.$store.dispatch("createProblem",problemData)
          console.log(p)
          data.problem.id = p.id
          this.problemas.push(p)
        }else{
          const k = this.$store.getters.getProblemaById(form.known)
          this.problemas.push(k)
          data.problem.id = form.known
        }
        this.$store.dispatch("linkProblema",data)
        this.newModal = false
      }
    }
  }
</script>
<style scoped>
#step{
  background-color: #F3F7FF;
  display: flex;
  gap: 20px;
  justify-content: center;
  gap: 5rem;
  padding: 0.8rem;
  font-size: 16px;
  margin-bottom: 20px;
}

#step span{
  cursor: pointer;
}
</style>