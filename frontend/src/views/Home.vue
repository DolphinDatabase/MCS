<template> 
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'/> 
  <BasePage>
    <Title title="Dashboard"/>
    <div class="cards">
      <el-card id="top-card" > 
        <div class="el-card__body">
          <div>
            <h1>Olá!</h1>
            <br/> <br/>
              <p>Este é o seu Dashboard, aqui você tem acesso aos principais indicadores de
              desempenho que são relevantes para o seu dia a dia no MCS.</p>
              <br/>
              <p>Gostaria de criar um &nbsp; <b>chamado</b>? Vamos lá!</p>
          </div>
          <img src="../assets/Home_Img.svg" style="width: 300px;"/>
        </div>
      </el-card>
      <div class="bottom">
        <div>
          <el-card id="first-card"> 
            <h2>Quantidade de Chamados</h2>
            <div id="grafico">
              <GChart
              type="ColumnChart"
              :data="chartData"
              :options="chartOptions"
              :size="500"
            />
            </div>
            <div id="legenda">
              <ul>
                <li id="vermei"><span>Aberto</span></li>
                <li id="amarel"><span>Em Andamento</span></li>
                <li id="verd"><span>Concluído</span></li>
              </ul>
            </div>

          </el-card>
        </div>
        <el-card id="scnd-card"> 
          <h2>Gerenciar Meus Chamados</h2>
          <div id="piegraph">
          <GChart
              type="PieChart"
              :data="chartData1"
              :options="chartOptions"
              :size="500"
            />
          </div>
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
        chartData: [
          ['Status', 'Chamados', { role: 'style' }],
          ['Aberto', 10, '#F56C6C'],
          ['Andamento', 80, '#FFB141'],
          ['Concluído', 9, '#7DEA42'],
        ],
        chartData1: [
        ['Status', 'Contagem'],
          ['Aberto', 12,],
          ['Concluído', 4],
        ]
      };
    }
  };
</script>

<style scoped>
  
  .cards{
    display: flex; 
    justify-content:center;
    flex-wrap: wrap;
  }
  .el-card__body{
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    
  }
  #top-card {
    padding:.5rem;
    height: 290px;
    width: 1130px;
    box-shadow: 1px 1px 15px rgba(0, 0, 0, 0.25);
    border-radius: 15px;
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
  .bottom{
    display: flex;
    justify-content: center;
    margin: 1.5rem;
    align-items: center;
  }
  #first-card{
    display: flex;
    margin-right: 1rem;
    width: 650px;
    box-shadow: 1px 1px 15px rgba(0, 0, 0, 0.25);
    border-radius: 15px;
  }
  #first-card h2{
    font-size: 18px;
  }
  #scnd-card{
    display: flex;
    margin-left: .5rem;
    width: 455px;
    box-shadow: 1px 1px 15px rgba(0, 0, 0, 0.25);
    border-radius: 15px;
  }
  #scnd-card h2{
    font-size: 18px;
  }
  #grafico{
    width: 450px;
    height: auto;
    stroke-width: 5px;
    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    color: #3F3F3F;
  }
  #piegraph{
    display: flex;
    width: 550px;
    height: auto;
    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    color: #3F3F3F;
  }
  #legenda{
    position: absolute;
    left: 859px;
    top: 604px;
  }
  #vermei{
    color: #F56C6C;
  }
  #amarel{
    color: #FFB141;
  }
  #verd{
    color: #7DEA42;
  }
  ul li span{
    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    color: #3F3F3F;
  }
</style>