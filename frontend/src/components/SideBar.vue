<template>
    <div class="menu">
        <ul>
            <li v-for="item in this.menu[this.auth.roles[0].id]" :key="item.path">
                <router-link :to="item.path">
                    <el-icon class="icon" :size="30">
                        <component :is="item.icon"/>
                    </el-icon>
                </router-link>
            </li>
        </ul>
    </div>
</template>
<script>
    import {ElMenu,ElIcon} from 'element-plus'
    import {RouterLink} from 'vue-router'
    
    export default {
        name: "SideBar",
        components:{
            ElMenu,ElIcon,RouterLink
        },
        data(){
            return{
                auth:this.$store.getters.getAuth,
                menu:{
                    1:[
                        {
                            path:"/",
                            icon:"House"
                        },{
                            path:"/usuarios",
                            icon:"User"
                        },{
                            path:"/chamados",
                            icon:"Files"
                        },{
                            path:"/orcamento",
                            icon:"Wallet"
                        }
                    ],
                    2:[
                        {
                            path:"/",
                            icon:"House"
                        },{
                            path:"/chamados",
                            icon:"Files"
                        }
                    ],
                    3:[
                        {
                            path:"/",
                            icon:"House"
                        },{
                            path:"/chamados",
                            icon:"Files"
                        }
                    ]
                }
            }
        },
        created(){
            console.log(this.menu[this.auth.roles[0].id])
        }
    }
</script>
<style scoped>
    .menu{
        background-color: var(--color-light);
        display: flex;
        flex-direction: column;
        margin-right: -40px; 
        box-shadow: 4px 4px 4px rgba(86, 144, 198, 0.11), 8px 8px 40px rgba(125, 153, 180, 0.2);
        border-radius: 47px;
        width: 55px;
        height: 340px;
        z-index: 10;
        margin-top: 1.75rem;
    } 

    ul{
        text-decoration: none;
        list-style: none;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding: 10px 0;
    }

    li{
        margin: 5px 0;
        padding: 12px;
        border-radius: 50em;
        transition: 0.5s;
    }

    li:hover{
        background-color: rgba(0, 0, 0, 0.2);
        transition: 0.5s;
    }

    .icon {
        color: var(--color-white);
    }

</style>