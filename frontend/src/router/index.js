import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Usuarios from '../views/Usuarios.vue'
import Chamados from '../views/Chamados.vue'
import ChamadoDetail from '../views/ChamadoDetail.vue'
import Orcamento from '../views/Orcamento.vue'
import Estoque from '../views/Estoque.vue'
import store from '../store'
import Falhas from '../views/Falhas.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta:{
        auth:true
      }
    },
    {
      path: '/login',
      name: 'login',
      component: Login 
    },
    {
      path: '/usuarios',
      name: 'usuarios',
      component: Usuarios,
      meta:{
        auth:true
      }
    },
    {
      path: '/chamados',
      name: 'chamados',
      component: Chamados,
      meta:{
        auth:true
      }
    },
    {
      path: '/chamado/:id',
      name: 'chamadoDetalhe',
      component: ChamadoDetail,
      meta:{
        auth:true
      }
    },
    {
      path: '/orcamento',
      name: 'orcamento',
      component: Orcamento,
      meta:{
        auth:true
      }
    },{
      path: '/estoque',
      name: 'estoque',
      component: Estoque,
      meta:{
        auth:true
      }
    },
    {
      path: '/falhas',
      name: 'falhas',
      component: Falhas,
      meta:{
        auth:true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const { auth } = to.meta;
  const currentUser = store.getters.getAuth;

  if (auth) {
      if (currentUser==null) {
          // not logged in so redirect to login page with the return url
          return next({ path: '/login'});
      }
  }

  next();
})

export default router
