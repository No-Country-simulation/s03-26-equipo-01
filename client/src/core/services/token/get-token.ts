import { TOKEN_KEY } from "./token-key";

function getToken(): string {
    const token = localStorage.getItem(TOKEN_KEY);
    if(!token) throw Error('No hay token de autenticación');
    return token;
}

export default getToken;