import {createStore} from 'vuex'

import roles from '../utils/roles'
 
import chamadoFunctions from '../api/chamados'
import problemaFunctions from '../api/problemas'
import usuarioFunctions from '../api/usuarios'
import http from '../api'
import sessionFunctions from '../api/session'
import orcamentoFunctions from '../api/orcamento/index'
import materialFunctions from '../api/material/index'
import chamadoproblemaFunctions from '../api/chamadoProblema/index'

const store = createStore({
    state(){
        return{
            data:{
                chamados:[],
                usuarios:[],
                roles:[
                    {
                        "id": 1,
                        "nivel": "ROLE_ADM"
                    },
                    {
                        "id": 2,
                        "nivel": "ROLE_SUP"
                    },
                    {
                        "id": 3,
                        "nivel": "ROLE_CLT"
                    }
                ],
                problemas:[
                ],
                nivel:[
                    {
                        "id": 1,
                        "nivel": "EASY"
                    },
                    {
                        "id": 2,
                        "nivel": "MEDIUM"
                    },
                    {
                        "id": 3,
                        "nivel": "HARD"
                    }
                ],
                materials:[],
                auth:null
            }
        }
    },
    getters:{
        getNivel:(state)=>{
            return state.data.nivel
        },
        getNivelById:(state)=>(id)=>{
            const nivels = state.data.nivel.find(r=>r.id==id)
            delete nivels.label
            return nivels
        },
        getAllMaterials(state){
            return state.data.materials
        },
        // role para aparecer o ícone
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
        getProblemaById:(state)=>(id)=>{
            return state.data.problemas.find(p=>p.id==id)
        },
        getAllUsuarios:(state)=>{
            const users = []
            state.data.usuarios.forEach(user=>{
                var uRoles = []
                user.roles.forEach(role=>{
                    uRoles.push(roles[role.nivel].label)
                })
                uRoles = uRoles.join(", ")
                users.push({id:user.id,name:user.name,cpf:user.document,email:user.email,roles:uRoles})
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
        }
    },
    actions:{
        async createProblem({commit},data){
            const res = await problemaFunctions.create(data)
            console.log(res)
            commit("addProblema",res.data)
            return res.data
        },
        async deleteProblema({commit},id){
            await problemaFunctions.delete(id)
            commit("deleteProblema",id)
        },
        async listChamados({commit}){
            const res = await chamadoFunctions.list()
            if(res.success){
                commit("listChamados",res.data)
            }
        },
        async listRoles({commit}){
            const req = await http.get("/role")
            const roles = req.data.data
            commit("asyncRoles",roles)
        },
        async listUsuarios({commit}){
            const all = await usuarioFunctions.list()
            commit("asyncUsuarios",all.data)
        },
        async createUser({commit},user){
            const n = await usuarioFunctions.create(user)
            commit("addUser",n.data)
        },
        async createOrcamento({commit},payload){
            const nOrcamento = await orcamentoFunctions.create(payload.form)
            if(nOrcamento.success){
                const chamado = await chamadoFunctions.find(payload.id)
                chamado.data.data.budget = nOrcamento.data
                chamado.data.data.status = "IN_PROGRESS"
                const nChamado = await chamadoFunctions.update(chamado.data.data)
                await commit("updateChamado",nChamado.data)
            }
        },
        async addMaterial({commit},payload){
            const data = {
                num:payload.form.num,
                model:payload.form.model,
                description:payload.form.description,
                quantity:payload.form.quantity,
                inventory:payload.form.inventory
            }
            await materialFunctions.update(data)
            await chamadoFunctions.addMaterial({id:payload.id,num:payload.form.num})
        },
        async updateUsuario({commit},usuario){
            const user = await usuarioFunctions.update(usuario)
            commit("addUser",user.data)
        },
        async deleteUsuario({commit},id){
            const res = await usuarioFunctions.delete(id)
            commit("deleteUsuario",id.data)
        },
        async addChamado({commit},chamado){
            const create = await chamadoFunctions.create(chamado)
            commit("newChamado",create.data)
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
            commit("asyncProblemas",all.data)
        },
        async linkProblema({commit},data){
            const link = await chamadoproblemaFunctions.create(data)
            commit("linkProblema",{link:link.data,id:data.solicitation.id})
        },
        async createSession({commit},auth){
            const data = await sessionFunctions.create(auth)
            if(data.success){
                commit("newSession",data.data)
                return true
            }else{
                throw data.message
            }
        },
        async updateChamado({commit},data){
            const edit = await chamadoFunctions.update(data)
            commit("newChamado",edit)
        },
        async listMaterials({commit}){
            const all = await materialFunctions.list()
            commit("asyncMaterial",all.data)
        },
        async createMaterials({commit},material){
            const materials = await materialFunctions.create(material)
            commit("createMaterial",materials.data)
        },
        async updateMaterials({commit},material){
            const materials = await materialFunctions.update(material)
            commit("createMaterial",materials.data)
        },
        async deleteMaterials({commit},id){
            const res = await materialFunctions.delete(id)
            commit("deleteMaterial",id.data)
        },
    },
    mutations:{
        deleteProblema(state,id){
            const pbl = state.data.problemas.find(p=>p.id==id)
            state.data.problemas.splice(pbl,1)
        },
        linkProblema(state,payload){
            const ch = state.data.chamados.find(c=>c.id==payload.id)
            if(ch.problems==null){
                ch.problems = [ch]
            }else{
                ch.problems.push(ch)
            }
        },
        addProblema(state,problema){
            state.data.problemas.push(problema)
        },
        asyncRoles(state, rls){
            rls.forEach(rl=>{
                rl.label = roles[rl.nivel].label
            })
            state.data.roles = rls
        },

        listChamados(state,all){
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

        addUser(state,user){
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
            state.data.auth = {
                role:auth.role,
                token:auth.token
            }
        },

        // EQUIPAMENTOS/ MATERIAL 
        asyncMaterial(state,all){
            state.data.materials = all
        },

        createMaterial(state,material){
            state.data.materials.push(material)
        },

        deleteMaterial(state,id){
            const mat = state.data.materials.find(u=>u.id==id)
            state.data.materials.splice(mat,1)
        },

        logout(state){
            state.data.auth = null
        }
    }
})

export default store;