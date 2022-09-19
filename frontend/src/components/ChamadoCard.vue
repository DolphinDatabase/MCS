<template>
    <card-list>
        <el-card v-for="card in this.dataCard" :key="card.id" shadow="hover" :class="this.status[card.status].class"> 
            <div class="card-header">
                <h3>{{card.name}}</h3>
                <div>
                    <p>{{dateDifference(card.date)}} dias atras</p>
                    <el-tag :type="this.status[card.status].type" effect="plain" round>{{this.status[card.status].label}}</el-tag>
                </div>
            </div>
            <hr/>
            <div class="card-info">
                <section>
                    <p>{{card.user.name}}</p>
                    <p>{{card.location.logradouro}}, Nº {{card.location.num}}, {{card.location.bairro}}</p>
                </section>
                <router-link :to="'/chamado/'+card.id">mais detalhes</router-link>
            </div>
        </el-card>
    </card-list>
</template>
<script>
import {ElCard,ElTag} from 'element-plus'
import {RouterLink} from 'vue-router'
import CardList from './CardList.vue'
import dateDifference from '../utils/dateDifference'
import status from '../utils/status'
export default {
    name:"ChamadoCard",
    components:{
        ElCard,
        ElTag,
        RouterLink,
        CardList
    },
    data(){
        return{
            dataCard:null,
            status:status,
        }
    },
    props:{
        auth:Object
    },
    methods:{
        dateDifference:dateDifference,
    },
    async mounted(){
        await this.$store.dispatch("listChamado")
        if(this.auth.roles[0].id==3){
            this.dataCard = this.$store.getters.getClienteChamados(this.auth.id)
        }else{
            this.dataCard = this.$store.getters.getAllChamados
        }
    }
}
</script>
<style scoped>
    #list{
        height: 420px;
    }

    .el-card{
        margin: 0 18px 18px 0;
        height: 190px;
    }

    .open{
        border-left: 10px solid var(--color-orange);
    }

    .andamento{
        border-left: 10px solid var(--color-light);
    }

    .finished{
        border-left: 10px solid var(--color-green);
    }

    .card-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 30px;
    }

    .card-header h3{
        width: 40%;
        font-size: 16px;
    }

    .card-header div{
        display: flex;
        justify-content: space-between;
        flex-direction: column;
        align-items: end;
    }

    .card-header div p{
        font-size: 8px;
        margin-bottom: 20px;
    }

    .card-info{
        font-size: 10px;
        margin-top: 10px;
        display: flex;
        flex-direction: column;
    }

    .card-info section p{
        margin-bottom: 10px;
    }

    .card-info a{
        color: #407BFF;
        font-size: 10px;
        text-decoration: none;
        text-align: center;
    }
</style>