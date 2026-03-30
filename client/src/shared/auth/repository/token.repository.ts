import { jwtDecode } from "jwt-decode";
import { TOKEN_KEY } from "../../../core/token-key/token-key";
import type { AxiosResponse } from "axios";
import type { Rol } from "../../types/user/rol";

export function setToken(response: AxiosResponse): void {
    localStorage.setItem(TOKEN_KEY, response.headers.authorization);
}

export function getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
}

export function getUserData(): UserTokenData {
    if (!getToken()) throw new Error('El token no se encuentra almacenado');
    const token = getToken() as string;
    return jwtDecode<UserTokenData>(token);
}

export interface UserTokenData {
    sub: string;
    role: Rol;
}