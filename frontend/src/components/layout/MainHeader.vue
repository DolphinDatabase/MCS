<template>
    <div id="mainHeader">
        <section id="topMenu">
            <ul>
                <li @click="logout()">
                    <p>Logout</p>
                    <img src="../../assets/Icons/Leave.svg" style="width: 15px;"/>
                </li>
            </ul>
        </section>
    </div>
</template>
<script>
    import {ElPageHeader} from 'element-plus'
    export default {
        name: "MainHeader",
        components:{
            ElPageHeader
        },
        methods:{
            logout(){
                this.$store.commit("logout")
                this.$router.push("/login")
            }
        }
    }
</script>
<style scoped>
    #mainHeader{
       display: flex;
       align-items: center;
       justify-content: flex-end;
       background: #FFFFFF;
       box-shadow: 0px 1px 15px rgba(0, 0, 0, 0.25);
       height: 7%;
       width: 100%;
       padding: 1rem;
       flex-direction: row;
    } 
    
    #topMenu ul{
        list-style: none;
        text-decoration: none;
        display: flex;
    }

    #topMenu ul li{
        cursor: pointer;
        display: flex;
    }

    #topMenu ul li p{
        color: #3F3F3F;
        font-size: 16px;
        margin: 1rem;
    }


</style>