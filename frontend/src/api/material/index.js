import http from '../index'

const materialFunctions = {
    async list(){
        try{
            const req = await http.get("/material")
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async create(material){
        try{
            const req = await http.post("/material",material)
            return req.data
        }catch(err){
            return err.response.data
        }
    },
    async update(material){
        try{
            const req = await http.put("/material/"+material.num,material)
            return req.data
        }catch(err) {
            return err.response.data
        }
    },
    async delete(id){
        try{
            const req = await http.delete("/material/"+id)
            return req.data.status
        }catch(err) {
            return err.response.data
        }
    }
}

export default materialFunctions