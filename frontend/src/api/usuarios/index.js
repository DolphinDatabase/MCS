import http from '../index'

const usuarioFunctions = {
    async list(){
        const req = await http.get("/user")
        return req.data.data
    },
    async create(usuario){
        const req = await http.post("/user",usuario)
        return req.data.data
    },
    async update(usuario){
        const req = await http.put("/user/"+usuario.id,usuario)
        return req.data.data
    },
    async delete(id){
        const req = await http.delete("/user/"+id)
        return req.data.status
    }
}

export default usuarioFunctions