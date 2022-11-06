<template> 
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'/> 
  <BasePage>
    <Title title="Dashboard"/>
    <div class="cards">
      <el-card class="card"> 
        <section id="top-card">
          <div>
            <h1>Olá!</h1>
            <p>Este é o seu Dashboard, aqui você tem acesso aos principais indicadores de
            desempenho que são relevantes para o seu dia a dia no MCS.</p>
          </div>
          <img src="../assets/Home_Img.svg" style="width: 300px;"/>
        </section>
      </el-card>
      <div id="charts">
        <el-card class="card">
          <h3>Quantidade de chamados</h3>
          <GChart
            type="PieChart"
            :data="this.chamadosChart.data"
            :options="this.chamadosChart.options"
            v-if="this.chamadosChart.total>0"
          />
          <p>Total de {{this.chamadosChart.total}} chamados</p>
        </el-card>
        <el-card class="card">
          <h3>Linha do tempo</h3>
          <el-select v-model="this.monthChart.year" placeholder="Select">
            <el-option
              v-for="(item,index) in this.monthChart.data"
              :key="index"
              :label="index"
              :value="index"
            />
          </el-select>
          <GChart
            type="LineChart"
            :data="this.monthChart.data[this.monthChart.year]"
          />
        </el-card>
      </div>
  </div>
  </BasePage>
</template>
<script>
  import BasePage from '../components/layout/BasePage.vue'
  import Title from '../components/content/Title.vue'
  import {ElCard} from 'element-plus'
  import CardList from '../components/content/CardList.vue'
  import {GChart} from 'vue-google-charts'
  export default{
    name:"Dashboard",
    components:{
      BasePage,
      Title,
      ElCard,
      CardList,
      GChart
    },
    data(){
      return{
        chamadosChart:{
          data:null,
          options:{
            slices: {
              0: { color: '#F56C6C' },
              1: { color: '#E6A23C' },
              2: { color: '#67C23A' }
            }
          },
          total:0
        },
        monthChart:{
          data:{},
          year:null
        }
      }
    },
    async created(){
      await this.$store.dispatch("listChamados")
      this.chamadosChart.data = this.$store.getters.getChamadoChartData
      this.monthChart.data = this.$store.getters.getMonthChartData
      this.monthChart.year = new Date().getFullYear()
      console.log(this.monthChart.data)
      this.chamadosChart.total = (this.chamadosChart.data[1][1]+this.chamadosChart.data[2][1]+this.chamadosChart.data[3][1]) 
    }
  };
</script>

<style scoped>
  
  .cards{
    display: flex; 
    justify-content:center;
    flex-direction: column;
    gap: 1rem;
    overflow: auto;
    max-height: 90%;
  }
  .card{
    padding: 0.5rem;
    border-radius: 15px;
  }
  #top-card{
    display: flex;
    justify-content: flex-start;
  }
  #top-card h1 {
    font-size: 25px;
    display: flex;
    align-self: flex-start;
  }
  #top-card p {
    font-size: 19px;
    display: flex;
    align-self: flex-start;
  }
  #charts{
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 1rem;
  }
  
</style>