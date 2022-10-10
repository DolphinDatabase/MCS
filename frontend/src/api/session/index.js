import http from '../index'

const sessionFunctions = {

    async create(auth){
        try{
            const res = await http.post("/session",auth)
            http.defaults.headers.common['Authorization'] = `Bearer ${res.data.data.token}`
            return res.data
        }catch(err){
            return err.response.data
        }
           
    }

}

export default sessionFunctions