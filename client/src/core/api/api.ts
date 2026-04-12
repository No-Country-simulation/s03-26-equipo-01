import axios, { AxiosError } from 'axios';
import handleErrors from './middlewares/error-handler/middlewares';
import sendToken from './middlewares/send-headers/send-token.middleware';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
});

api.interceptors.request.use(
  (request) => sendToken(request),
  (error: AxiosError) => handleErrors(error),
);

api.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => handleErrors(error),
);

export default api;
