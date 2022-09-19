<template>
    <el-table :data="this.tableData" style="width: 100%; font-size: 20px;">
      <el-table-column prop="name" label="Nome" />
      <el-table-column fixed="right" label="Ações" width="100">
        <template #default="scope">
            <el-button link style="color: var(--color-primary-variant)" @click="openModal(scope.row)">
                <el-icon :size="20">
                    <Document/>
                </el-icon>
            </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
        v-model="modal"
        width="70%"
    >
        <div class="header">
            <div class="row">
                <h1 class="title">{{this.select.name}}</h1>
            </div>
        </div>
        <div class="sub-info">
            <p>Cliente: {{this.select.user.name}}</p>
            <p>Localização: {{this.select.location.logradouro}}, Nº {{this.select.location.num}}, {{this.select.location.bairro}}</p>
            <p>Data: {{this.date}}</p>
        </div>
        <hr/>   
        <div class="info">
            <p style="font-weight:bold">Descrição</p>
            <p>{{this.select.description}}</p>
            <span v-if="(this.select.problems!=null && this.select.problems.length>0)">
                <p  style="font-weight:bold">Problemas Encontrados</p>
                <p v-for="p in this.select.problems" :key="p.id">{{p.name}}</p>
            </span>
            <p style="font-weight:bold">Orçamento</p>
            <el-input type="number" v-model="this.orcamento"/>      
        </div>
        <template #footer>
        <span class="dialog-footer">
            <el-button @click="this.modal = false">Cancel</el-button>
            <el-button type="primary" @click="closeChamado()">Concluir</el-button>
        </span>
        </template>
    </el-dialog>
</template>
<script>
    import dateDifference from '../utils/dateDifference'
    import dayjs from 'dayjs'
    import { ElTable, ElTableColumn, ElButton, ElIcon } from 'element-plus'
    export default {
    name:"MainTable",
    components:{
        ElTable,
        ElTableColumn,
        ElButton,
        ElIcon
    },
    data(){
        return{
            tableData:null,
            modal:false,
            orcamento:null,
            date:null,
            select:{
                id: null,
                name: null,
                description: null,
                date: null,
                status: null,
                location: {
                    id: null,
                    cep: null,
                    bairro: null,
                    cidade: null,
                    complemento: null,
                    logradouro: null,
                    uf: null,
                    num: null
                },
                user: {
                    name: null,
                },
                problems: []
            }
        }
    },
    methods:{
        dateDifference:dateDifference,
        openModal(scope){
            this.select=scope
            this.date = dayjs(this.select.date).format("DD/MM/YYYY HH:mm:ss")
            this.modal = true
        },
        async closeChamado(){
            this.select.status = "FINISHED"
            await this.$store.dispatch("updateChamado",this.select)
            await this.$store.dispatch("listChamado")
            this.tableData = this.$store.getters.getAndamento
            this.modal = false
        }
    },
    async mounted(){
        await this.$store.dispatch("listChamado")
        this.tableData = this.$store.getters.getAndamento
    }
}
</script> 
<style scoped>
    .title {
        font-family: 'Roxborough CF';
        font-style: normal;
        color: var(--color-primary);
    }

    .header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 20px;
    }
    .header div{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    .sub-info{
        font-size: 15px;
        margin-bottom: .5rem;
    }

    .sub-info p{
        padding: 5px;
    }

    .info{
        font-size: 17px;
        margin-bottom: .5rem;
    }

    .info p{
        padding: 5px;
    }
</style>