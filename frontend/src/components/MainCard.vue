<template>
    <el-card class="card">
        <slot></slot>
    </el-card>
</template>
<script>
    import {ElCard} from 'element-plus'
    export default {
        name: "MainCard",
        components:{
            ElCard
        }
    }
</script>
<style scoped>
    .card{
        background-color: var(--color-white);
        height: 100%;
    } 

</style>