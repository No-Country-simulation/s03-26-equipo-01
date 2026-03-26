import { TOKEN_KEY } from "../../../core/token-key/token-key";

export function setToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
}

export function getToken(): void {
    localStorage.getItem(TOKEN_KEY);
}
