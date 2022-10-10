<template>
    <card-list v-if="this.chamados.length>0">
        <el-card style="cursor:pointer" @click="()=>this.$emit('click',chamado)" :class="'cardChamado '+this.status[chamado.status].class" v-for="chamado in this.chamados" :key="chamado.id" shadow="hover"> 
            <div class="cardDate">
                <span>{{dateDifference(chamado.date)}} dia(s) atrás</span>
            </div>
            <div class="cardContent">
                <h1>{{chamado.name}}</h1>
                <span>{{chamado.user.name}}</span>
            </div>
            <div class="cardTag">
                <el-tag :type="this.status[chamado.status].type" effect="light">{{this.status[chamado.status].label}}</el-tag>
                <el-tag v-if="chamado.budget!=null" type="primary" effect="light">{{(chamado.budget.total).toLocaleString('pt-BR', { minimumFractionDigits: 2 , style: 'currency', currency: 'BRL' })}}</el-tag>
            </div>
        </el-card>
    </card-list>
    <span v-else>Nenhum chamado</span>
</template>
<script>
import {ElCard,ElTag} from 'element-plus'
import {RouterLink} from 'vue-router'
import CardList from './CardList.vue'
import dateDifference from '../../utils/dateDifference'
import status from '../../utils/status'
export default {
    name:"ChamadoList",
    components:{
        ElCard,
        ElTag,
        RouterLink,
        CardList
    },
    data(){
        return{
            status:status
        }
    },
    props:{
        chamados:Array
    },
    methods:{
        dateDifference:dateDifference,
    }
}
</script>
<style scoped>
#list{
    max-height: 85%;
}

.open{
    border-left: 10px solid var(--color-red);
}

.andamento{
    border-left: 10px solid var(--color-orange);
}
    
.finished{
    border-left: 10px solid var(--color-green);
}

.cardChamado{
    padding: 0 0 0 1.5rem;
    border-radius: 15px;
}

.cardDate{
    display: flex;
    justify-content: end;
    font-size: 14px;
    color: #757575;
    margin-bottom: 20px;
}

.cardContent{
    margin-bottom: 20px;
}

.cardContent h1{
    font-size: 20px;
    margin-bottom: 15px;
}

.cardContent span{
    font-size: 16px;
    color: #757575;
    margin-bottom: 15px;
}

.cardTag{
    display: flex;
    gap: 0 10px;
}

</style>