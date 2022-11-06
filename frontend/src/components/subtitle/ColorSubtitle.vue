<template>
    <div id="subtitle">
        <div v-for="i in this.data" :key="i.color">
            <div class="circle" :style="'background-color:'+i.color"/>
            <p>{{i.name}}</p>
        </div>
    </div>    
</template>
<script>
export default {
    name:"ColorSubtitle",
    props:{
        data:Array
    }
}
</script>
<style scoped>
    #subtitle{
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 10px;
    }

    #subtitle div{
        display: flex;
        align-items: center;
    }

    #subtitle div p{
        margin: 0;
    }

    .circle{
        width: 12px;
        height: 12px;
        border-radius: 50%;
        margin-right: 0.5rem;
    }
</style>