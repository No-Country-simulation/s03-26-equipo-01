import type { AxiosResponse } from "axios";
import { TOKEN_KEY } from "./token-key";

function setToken(response: AxiosResponse): void {
    localStorage.setItem(TOKEN_KEY, response.headers.authorization);
}

export default setToken;