import http  from "../index";

const chamadoproblemaFunctions = {
    async create(data){
        try{
            const res = await http.post("/SolicitationProblem",data)
            return res.data
        }catch(err){
            return err.response.data
        }
    }
}

export default chamadoproblemaFunctions