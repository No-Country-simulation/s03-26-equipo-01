import type { User } from "../../../shared/user/models/user";
import { TOKEN_KEY } from "./token-key";

function removeToken(user: User) {
    localStorage.removeItem(TOKEN_KEY);
}

export default removeToken;