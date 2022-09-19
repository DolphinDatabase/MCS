<template>  
  <div id="home">
    <SideBar/>
    <main-card>
      <main-header/>
    </main-card>
  </div>
</template>
<script>
  import MainCard from '../components/MainCard.vue'
  import SideBar from '../components/SideBar.vue'
  import MainHeader from '../components/MainHeader.vue'
  export default{
    name:"Home",
    components:{
      MainCard,
      SideBar,
      MainHeader
    }
  }
</script>
<style>
  #home{
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>