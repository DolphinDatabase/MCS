import http from '../index'

const chamadoFunctions = {
    async list(){
        const req = await http.get("/solicitation")
        return req.data.data
    },
    async find(id){
        return await http.get("/solicitation/"+id)
    },
    async create(data){
        const req = await http.post("/solicitation",data)
        if(req.status==200){
            return true
        }
        else{
            return false
        }
    },
    async update(data){
        var up = {
            name: data.name,
            description: data.description,
            status: data.status,
            location: data.location 
        }
        const req = await http.put(`/solicitation/${data.id}`,up)
        return req.data.data
    }
}

export default chamadoFunctions