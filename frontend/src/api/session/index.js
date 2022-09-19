import http from '../index'

const sessionFunctions = {

    async create(auth){
        return await http.post("/session",auth)
    }

}

export default sessionFunctions