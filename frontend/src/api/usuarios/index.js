import http from '../index'

const usuarioFunctions = {
    async list(){
        try{
            const req = await http.get("/user")
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async create(usuario){
        try{
            const req = await http.post("/user",usuario)
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async update(usuario){
        try{
            const req = await http.put("/user/"+usuario.id,usuario)
            return req.data
        }catch(err) {
            return err.response.data
        }
    },
    async delete(id){
        try{
            const req = await http.delete("/user/"+id)
            return req.data.status
        }catch(err) {
            return err.response.data
        }
    }
}

export default usuarioFunctions