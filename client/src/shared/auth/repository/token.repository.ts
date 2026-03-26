import { TOKEN_KEY } from "../../../core/token-key/token-key";

function tokenService(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
}

export default tokenService;