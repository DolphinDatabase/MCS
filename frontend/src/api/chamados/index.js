import http from '../index'

const chamadoFunctions = {
    async list(){
        try{
            const req = await http.get("/solicitation")
            return req.data
        }catch(err){
            err.response.data
        }
    },
    async find(id){
        return await http.get("/solicitation/"+id)
    },
    async create(data){
        try{
            const res = await http.post("/solicitation",data)
            return res.data
        }catch(err){
            err.response.data
        }
    },
    async update(data){
        try{
            var up = {
                name: data.name,
                description: data.description,
                status: data.status,
                location: data.location,
                budget: data.budget
            }
            const req = await http.put(`/solicitation/${data.id}`,up)
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async addMaterial(data){
        try{
            const res = await http.put(`/solicitation/${data.id}/addMaterial`,{num:data.num})
            return res.data
        }catch(err){
            return err.response.data
        }
    }
}

export default chamadoFunctions