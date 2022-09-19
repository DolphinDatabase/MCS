import axios from 'axios'
const http = axios.create({
    baseURL:"https://brasilapi.com.br/api/"
})

export default http