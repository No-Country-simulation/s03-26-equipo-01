import type { InternalAxiosRequestConfig } from "axios";
import { getTokenIs } from "../../../services/token/get-token";

function sendToken(request: InternalAxiosRequestConfig): InternalAxiosRequestConfig {
    const isToken = getTokenIs();
    if (isToken) request.headers.Authorization = isToken;
    return request;
}

export default sendToken;