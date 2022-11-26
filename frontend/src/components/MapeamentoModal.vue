<template>
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
            <Mapeamento v-if="this.step==0" :data="this.data.mapeamentos" :colors="this.data.cores" @cancel="this.$emit('cancel')" @openAnomalia="(id)=>this.openAnomalia(id)"/>
            <Mapa v-if="this.step==1" :data="this.mapping" :colors="this.data.cores" @changeStep="(step)=>this.changeStep(step)"/>
        </div>
      </div>
    </div>
</template>
<script>
import Mapeamento from './mapping/Mapeamento.vue'
import Mapa from './mapping/Mapa.vue'

export default {
    name:"MapeamentoModal",
    components:{
        Mapeamento,
        Mapa
    },
    data(){
        return{
            step:0,
            mapping:null
        }
    },
    props:{
        data:Object
    },
    methods:{
        changeStep(step){
            this.step=step
        },
        openAnomalia(id){
            this.mapping = this.data.mapeamentos.find(m=>m.id==id)
            this.changeStep(1)
        }
    }
}
</script>
<style scoped>
    .modal-mask {
        position: fixed;
        z-index: 12;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        display: table;
        transition: opacity 0.3s ease;
    }

    .modal-wrapper {
        display: table-cell;
        vertical-align: middle;
    }

    .modal-container {
        width: 85%;
        height: 85%;
        margin: 0px auto;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
        transition: all 0.3s ease;
        font-family: Helvetica, Arial, sans-serif;
    }

    #mapeamento{
        width: 100%;
        height: 100%;
        display: flex;
    }

    ::v-deep #panel{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 25%;
        padding: 2rem;
        font-size: 16px;
        box-shadow: 1px 0px 25px rgba(0, 0, 0, 0.25);
        border-radius: 10px;
        height:100%
    }

    ::v-deep #panel h1{
        margin: 0 1rem 1.5rem;
    }

    ::v-deep #panel p{
        font-weight: bold;
        margin-top: 1rem;
    }

    ::v-deep .box{
        width: 270px;
        margin-bottom: 1rem;
        box-shadow: 1px 0px 20px rgba(0, 0, 0, 0.25);
        border-radius: 10px;
        padding: .5rem;
        display: flex;
        flex-direction: column;
    }

    ::v-deep .groupButton{
        display: flex;
        justify-content: flex-end;
        gap: 1rem
    }
</style>