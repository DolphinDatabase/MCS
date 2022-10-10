<template>
    <div id="title">
        <section>
            <h1>{{this.title}}</h1>
            <div id="subtitle" v-if="this.subtitle!=null">
                <img src="../../assets/Icons/Info.svg" style="width: 12px; margin:3px 7px;"/>
                <h2>{{this.subtitle}}</h2> 
            </div>
        </section>
        <div>
            <slot></slot>
        </div>
    </div>
</template>
<script>
export default {
    name:"Title",
    props:{
        title:String,
        subtitle:String
    }
}
</script>
<style scoped>
    #title{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 2rem;
    }
    #title section{
        display: flex;
        align-items: flex-start;
        flex-direction: column;
    }

    #title section h1{
        font-size: 30px;
    }

    #subtitle{
        display: flex;
        align-items: flex-start;
        flex-direction: row;
        margin-bottom: 2rem;
    }

    #subtitle h2{
        font-size: 15px;
        color: #A5A5A5;
        font-weight: lighter;
    }
</style>