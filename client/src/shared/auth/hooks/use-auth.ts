import { useState } from "react";
import type { AuthCredentials } from "../models/user-credentials";
import type { User } from "../../user/models/user";
import loginService from "../service/login.service";
import useUserNavegate from "./use-home-navegate";
import useApi from "../../../core/api/hooks/use-api";
import logoutService from "../service/logout.service";

const useAuth = () => {
    
    const [user, setUser] = useState<User | null>(null);
    const {error, post, refreshError} = useApi<User>();
    const {redirectHomeTo, redirectMainTo} = useUserNavegate();

    async function login(credentials: AuthCredentials) {
        const user = await post(loginService, credentials);
        setUser(user);
        redirectHomeTo(user);
    }

    async function logout(user: User) {
        logoutService(user)
        setUser(null);
        redirectMainTo();
    }

    const closeError = () => refreshError();

    return {user, error, login, logout, closeError}
}

export default useAuth;