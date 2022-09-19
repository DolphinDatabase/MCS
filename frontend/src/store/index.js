import {createStore} from 'vuex'

import roles from '../utils/roles'
 
import chamadoFunctions from '../api/chamados'
import problemaFunctions from '../api/problemas'
import usuarioFunctions from '../api/usuarios'
import http from '../api'
import sessionFunctions from '../api/session'

const store = createStore({
    state(){
        return{
            data:{
                chamados:[],
                usuarios:[],
                roles:[],
                problemas:[],
                auth:null
            }
        }
    },
    getters:{
        getAuth:(state)=>{
            return state.data.auth
        },
        getRoles:(state)=>{
            return state.data.roles
        },
        getRoleById:(state)=>(id)=>{
            const role = state.data.roles.find(r=>r.id==id)
            delete role.label
            return role
        },
        getAllChamados:(state)=>{
            return state.data.chamados
        },
        getChamadoByid:(state)=>(id)=>{
            return state.data.chamados.find(chamado=>chamado.id==id)
        },
        getAllUsuarios:(state)=>{
            const users = []
            state.data.usuarios.forEach(user=>{
                var uRoles = []
                user.roles.forEach(role=>{
                    uRoles.push(roles[role.nivel].label)
                })
                uRoles = uRoles.join(", ")
                users.push({id:user.id,name:user.name,email:user.email,roles:uRoles})
            })
            return users
        },
        getUsuarioById:(state)=>(id)=>{
            const user = state.data.usuarios.find(u=>u.id==id)
            return user
        },
        getAllProblemas:(state)=>{
            return state.data.problemas
        },
        getClienteChamados:(state)=>(id)=>{
            const ch = []
            state.data.chamados.forEach(c=>{
                if(c.user.id==id){
                    ch.push(c)
                }
            })
            return ch
        },
        getAndamento:(state)=>{
            const and = []
            state.data.chamados.forEach(c=>{
                if(c.status=="IN_PROGRESS"){
                    and.push(c)
                }
            })
            return and
        }
    },
    actions:{
        async listRoles({commit}){
            const req = await http.get("/role")
            const roles = req.data.data
            commit("asyncRoles",roles)
        },
        async listUsuarios({commit}){
            const all = await usuarioFunctions.list()
            commit("asyncUsuarios",all)
        },
        async addUsuario({commit},usuario){
            const user = await usuarioFunctions.create(usuario)
            commit("newUsuario",user)
        },
        async updateUsuario({commit},usuario){
            const user = await usuarioFunctions.update(usuario)
            commit("newUsuario",user)
        },
        async deleteUsuario({commit},id){
            const res = await usuarioFunctions.delete(id)
            commit("deleteUsuario",id)
        },
        async addChamado({commit},chamado){
            const create = await chamadoFunctions.create(chamado)
            commit("newChamado",create)
        },
        async listChamado({commit}){
            const all = await chamadoFunctions.list()
            commit("asyncChamado",all)
        },
        async findChamado({commit},id){
            const chamado = await chamadoFunctions.find(id)
            commit("asyncChamado",[chamado])
        },
        async listProblema({commit}){
            const all = await problemaFunctions.list()
            commit("asyncProblemas",all)
        },
        async addProblema({commit},{idChamado,problema}){
            var id = null
            if(!problema.id){
                const newProblema = await problemaFunctions.create(problema)
                id = newProblema.id
            }else{
                id = problema.id
            }
            const create = await problemaFunctions.link(idChamado,id)
            commit("updateChamado",create)
        },
        async createSession({commit},auth){
            const data = await sessionFunctions.create(auth)
            if(data.data.success){
                commit("newSession",data.data.data)
                return data.data.data
            }else{
                throw data.data.message
            }
        },
        async updateChamado({commit},data){
            const edit = await chamadoFunctions.update(data)
            commit("newChamado",edit)
        }
    },
    mutations:{
        asyncRoles(state, rls){
            rls.forEach(rl=>{
                rl.label = roles[rl.nivel].label
            })
            state.data.roles = rls
        },

        asyncChamado(state,all){
            state.data.chamados = all
        },

        updateChamado(state,chamado){
            let ch = state.data.chamados.find(c=>c.id==chamado.id)
            ch = chamado
        },

        newChamado(state,chamado){
            state.data.chamados.push(chamado)
        },

        asyncUsuarios(state,all){
            state.data.usuarios = all
        },

        newUsuario(state,user){
            state.data.usuarios.push(user)
        },

        deleteUsuario(state,id){
            const usr = state.data.usuarios.find(u=>u.id==id)
            state.data.usuarios.splice(usr,1)
        },

        asyncProblemas(state,all){
            state.data.problemas = all
        },

        newSession(state,auth){
            state.data.auth = auth
        },

        logout(state){
            state.data.auth = null
        }
    }
})

export default store;