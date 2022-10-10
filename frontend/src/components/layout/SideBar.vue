<template>
  <div class="background">
    <img
      src="../../assets/WhiteLogo.svg"
      style="width: 50px; margin-top: 2rem"
    />
    <div class="menu">
      <ul>
        <li v-for="item in this.menu[this.auth.role]" :key="item.path">
          <router-link :to="item.path">
            <el-tooltip
              class="box-item"
              effect="customized"
              :content="item.tooltip"
              placement="right-start"
            >
              <el-icon class="icon" :size="30">
                <component :is="item.icon" />
              </el-icon>
            </el-tooltip>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { ElMenu, ElIcon, ElTooltip } from "element-plus";
import { RouterLink } from "vue-router";

export default {
  name: "SideBar",
  components: {
    ElMenu,
    ElIcon,
    RouterLink,
    ElTooltip,
  },
  data() {
    return {
      auth: this.$store.getters.getAuth,
      menu: {
        ROLE_ADM: [
          {
            path: "/",
            icon: "PieChart",
            tooltip: "Dashboard",
          },
          {
            path: "/usuarios",
            icon: "User",
            tooltip: "Usuário",
          },
          {
            path: "/chamados",
            icon: "Files",
            tooltip: "Chamados",
          },
          {
            path: "/estoque",
            icon: "PriceTag",
            tooltip: "Estoque",
          },
          {
            path: "/falhas",
            icon: "Document",
            tooltip: "Falhas e Soluções",
          },
        ],
        ROLE_SUP: [
          {
            path: "/",
            icon: "PieChart",
            tooltip: "Dashboard",
          },
          {
            path: "/chamados",
            icon: "Files",
            tooltip: "Chamados",
          },
          {
            path: "/estoque",
            icon: "PriceTag",
            tooltip: "Estoque",
          },
          {
            path: "/falhas",
            icon: "Document",
            tooltip: "Falhas e Soluções",
          },
        ],
        ROLE_CLT: [
          {
            path: "/",
            icon: "PieChart",
            tooltip: "Dashboard",
          },
          {
            path: "/chamados",
            icon: "Files",
            tooltip: "Chamados",
          },
        ],
      },
    };
  },
};
</script>
<style scoped>
.background {
  background-image: url("../../assets/BgLogin.svg");
  width: 10%;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu {
  background-color: var(--color-white);
  display: flex;
  flex-direction: column;
  background: #ffffff;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
  width: 60px;
  height: 400px;
  z-index: 10;
  margin: 1.75rem 0 0 7.75rem;
  align-items: center;
}

ul {
  text-decoration: none;
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 10px 0;
}

li {
  display: flex;
  margin: 5px 0;
  border-radius: 50em;
  transition: 0.5s;
  width: 50px;
  height: 50px;
  align-items: center;
  justify-content: center;
}

li:hover {
  background-color: rgba(0, 0, 0, 0.2);
  transition: 0.5s;
}

.icon {
  color: #3f3f3f;
}
</style>
