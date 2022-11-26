<template>
    <el-table  :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" height="85%">
        <el-table-column v-for="(item,index) in index" :key="index" :prop="index" :label="item">
            <template v-if="index=='nivel'" #default="scope">
                <el-tag :type="nivels[scope.row.nivel].type">{{nivels[scope.row.nivel].label}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column>
            <template #header>
                <el-input v-model="search" size="medium" placeholder="Busca" />
            </template>
        </el-table-column>
        <el-table-column type="expand">
            <template #default="scope">
                <slot :data="scope.row"></slot>
            </template>
        </el-table-column>
    </el-table>
</template>
<script>
import {ElTable,ElTableColumn} from 'element-plus'
import nivel from '../utils/nivel'

export default{
    name:"Table",
    data(){
        return{
            nivels:nivel,
            search: ''
        }
    },
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