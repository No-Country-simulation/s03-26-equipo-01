import axios, { AxiosError } from 'axios';
import handleErrors from './middlewares/error-handler/middlewares';
import sendToken from './middlewares/send-headers/send-token.middleware';

const API_BASE_URL =
  import.meta.env.VITE_API_URL || 'http://localhost:8081/api/v1';

const api = axios.create({
  baseURL: API_BASE_URL,
});

if (API_BASE_URL.includes('ngrok')) {
  api.defaults.headers.common['ngrok-skip-browser-warning'] = 'true';
}

api.interceptors.request.use(
  (request) => sendToken(request),
  (error: AxiosError) => handleErrors(error),
);

api.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => handleErrors(error),
);

export default api;
