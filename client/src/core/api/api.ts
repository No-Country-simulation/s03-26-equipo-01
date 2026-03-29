import axios, { AxiosError } from "axios";
import handleErrors from "./middlewares/middlewares";

const api = axios.create({
    baseURL: 'http://localhost:8080'
})

api.interceptors.response.use(
    (response) => response,
    (error: AxiosError) => handleErrors(error)
)

export default api;