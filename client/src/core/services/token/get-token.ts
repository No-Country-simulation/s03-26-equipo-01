import { TOKEN_KEY } from "./token-key";

function getToken(): string {
    const token = localStorage.getItem(TOKEN_KEY);
    if(!token) throw new Error('No hay token de autenticación');
    return token;
}

export function getTokenIs(): string | null {
    return localStorage.getItem(TOKEN_KEY);
} 

export default getToken;