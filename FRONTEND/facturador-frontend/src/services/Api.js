import axios from 'axios'
//'Content-Type': 'application/json'
export default() => {
    return axios.create({
        baseURL: `http://localhost:8080/`,
        withCredentials: false      
    })
}
