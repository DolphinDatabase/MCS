<template>  
  <div id="page">
    <SideBar/>
    <div id="main">
      <main-header/>
      <div id="content">
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script>
  import SideBar from './SideBar.vue'
  import MainHeader from './MainHeader.vue'
  export default{
    name:"BasePage",
    components:{
      SideBar,
      MainHeader
    }
  }
</script>
<style scoped>
#main{
  width: 100%;
  height: 100%;
}

#content{
  padding: 2rem 3rem;
  height: 93%;
}
</style>