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
        box-shadow: 4px 4px 4px rgba(86, 144, 198, 0.11), 8px 8px 40px rgba(125, 153, 180, 0.2);
        border-radius: 18px;
        position: sticky;
        height: 85%;
        width: 85%;
        padding-left: 40px;
        padding: 0;
    } 

</style>