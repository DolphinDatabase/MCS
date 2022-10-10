<template>
    <el-table :data="this.tableData" height="95%">
        <el-table-column v-for="(item,index) in index" :prop="index" :label="item" />
    </el-table>
</template>
<script>
import {ElTable,ElTableColumn} from 'element-plus'
export default{
    name:"Table",
    components:{
        ElTable,
        ElTableColumn
    },
    props:{
        index:Object,
        tableData:Array
    }
}
</script>
