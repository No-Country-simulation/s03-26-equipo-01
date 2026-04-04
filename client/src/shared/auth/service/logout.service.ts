import removeToken from "../../../core/services/token/remove-token";
import type { User } from "../../user/models/user";

function logoutService(user: User) {
    removeToken(user);
}

export default logoutService;