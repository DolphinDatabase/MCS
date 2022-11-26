import axios from 'axios' 

const http = axios.create({
    baseURL:"http://144.22.249.153:8080"
})

export default http