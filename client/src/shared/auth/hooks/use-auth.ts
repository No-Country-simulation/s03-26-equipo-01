import { useState } from "react";
import type { AuthCredentials } from "../models/user-credentials";
import type { User } from "../../user/models/user";
import loginService from "../service/login.service";
import useHomeNavegate from "./use-home-navegate";
import useApi from "../../../core/api/hooks/use-api";

const useAuth = () => {
    
    const [user, setUser] = useState<User | null>(null);
    const {error, post, refreshError} = useApi<User>();
    const {redirectTo} = useHomeNavegate();

    async function login(credentials: AuthCredentials) {
        const user = await post(loginService, credentials);
        setUser(user);
        redirectTo(user);
    }

    const closeError = () => refreshError();

    return {user, error, login, closeError}
}

export default useAuth;