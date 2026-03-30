import { useState } from "react";
import type { UserCredentials } from "../models/user-credentials";
import type { User } from "../../types/user/user";
import loginService from "../service/login.service";
import useUserNavegate from "./use-user-navegate";
import useApi from "../../../core/api/hooks/use-api";

const useAuth = () => {
    
    const [user, setUser] = useState<User | null>(null);
    const {error, post, refreshError} = useApi<User>();
    const {redirectTo} = useUserNavegate();

    async function login(credentials: UserCredentials) {
        const user = await post(loginService, credentials);
        setUser(user);
        redirectTo(user);
    }

    const closeError = () => refreshError();

    return {user, error, login, closeError}
}

export default useAuth;