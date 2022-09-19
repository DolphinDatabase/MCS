import http from '../index'

const problemaFunctions = {
    async create(data){
        const req = await http.post("/problem",data)
        return req.data.data
    },
    async link(idChamado,idProblema){
        const req = await http.put("/solicitation/"+idChamado+"/addProblem",{id:idProblema})
        return req.data.data
    },
    async list(){
        const req = await http.get("/problem")
        return req.data.data
    }
}

export default problemaFunctions