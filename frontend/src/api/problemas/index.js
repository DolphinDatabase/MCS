import http from '../index'

const problemaFunctions = {
    async create(data){
        try{
            const req = await http.post("/problem",data)
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async list(){
        try{
            const req = await http.get("/problem")
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async delete(id){
        try{
            const req = await http.delete("/problem/"+id)
            return req.data.status
        }catch(err) {
            return err.response.data
        }
    }
}

export default problemaFunctions