<template>
    <card-list>
        <el-card v-for="item in problemas" :key="item.id" :class="nivel[item.nivel].class">
            <div class="card-header">
                <h3>{{item.name}}</h3>
                <el-tag :type="nivel[item.nivel].type" effect="plain" round>Risco {{nivel[item.nivel].label}}</el-tag>
            </div>
            <hr/>
            <div class="solutions">
                <h5>Soluções conhecidas</h5>
                <ul>
                    <li v-for="s in item.solutions" :key="s.id">{{s.description}}</li>
                </ul>
            </div>
        </el-card>
    </card-list>
</template>
<script>
import {ElCard,ElTag} from 'element-plus'
import CardList from './content/CardList.vue'
import nivel from '../utils/nivel'
export default {
    name:"ProblemaCard",
    components:{
        ElCard,
        ElTag,
        CardList
    },
    data(){
        return{
            nivel:nivel,
            problemas:[]
        }
    }
}
</script>
<style scoped>
    #list{
        height: 350px;
    }

    .el-card{
        margin: 0 18px 18px 0;
        height: 190px;
    }

    .easy{
        border-left: 10px solid var(--color-green);
    }

    .medium{
        border-left: 10px solid var(--color-orange);
    }

    .hard{
        border-left: 10px solid var(--color-red);
    }

    .card-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 10px;
    }

    .card-header h3{
        width: 40%;
        font-size: 18px;
    }

    .solutions{
        padding: 10px 0 0 20px;
        font-size: 14px;
    }

    .solutions h5{
        font-size: 15px;
        margin-bottom: 8px;
    }
</style>