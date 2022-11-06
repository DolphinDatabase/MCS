import http from '../index'

export default {
    async create(data){
        try{
            const res = await http.post("/SolicitationMapping",data)
            return res.data
        }
        catch(err){
            return err.response.data
        }
    },
    async addLayer(data){
        try{
            const res = await http.put(`/SolicitationMapping/${data.id}/addLayer`,data.layers)
            return res.data
        }catch(err){
            return err.response.data
        }
    }
}