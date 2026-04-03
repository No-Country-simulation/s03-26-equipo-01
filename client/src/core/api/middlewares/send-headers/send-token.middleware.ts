import type { InternalAxiosRequestConfig } from "axios";
import getToken from "../../../services/token/get-token";

function sendToken(request: InternalAxiosRequestConfig): InternalAxiosRequestConfig {
    request.headers.Authorization = getToken();
    return request;
}

export default sendToken;