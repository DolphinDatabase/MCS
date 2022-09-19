<template>
    <div class="header">
        <img class="img" src="@/assets/MCS.png"/>
        <section id="topMenu">
            <ul>
                <li @click="logout()">logout</li>
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
    .header{
       display: flex;
       align-items: center;
       justify-content: space-between;
    } 

    .img {
        width: 250px;
    }
    
    #topMenu ul{
        list-style: none;
        text-decoration: none;
        display: flex;
    }

    #topMenu ul li{
        margin-left: 10px;
        cursor: pointer;
    }

</style>