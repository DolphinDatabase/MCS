import http from '../index'

const orcamentoFunctions = {
    async create(data){
        try{
            const res = await http.post("/budget",data)
            return res.data
        }catch(err){
            return err.response.data
        }
    }
}

export default orcamentoFunctions