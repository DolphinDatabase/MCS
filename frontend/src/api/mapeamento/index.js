import http from '../index'

export default {
    async create(data){
        try{
            const req = await http.post("/mapping",data)
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async list(){
        try{
            const res = await http.get("/mapping")
            return res.data
        }catch(err){
            return err.response.data
        }
    }
}