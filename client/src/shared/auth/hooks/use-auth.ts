import type { AuthCredentials } from "../models/user-credentials";
import type { User } from "../../user/models/user";
import loginService from "../service/login.service";
import useUserNavegate from "./use-home-navegate";
import useApi from "../../../core/api/hooks/use-api";
import logoutService from "../service/logout.service";
import useUser from "../../user/hooks/use-user";
import { useEffect } from "react";

const useAuth = () => {
    
    const {user, findUser, saveUser, refreshUser} = useUser();
    const {error, isLoading, post, refreshError} = useApi<User>();
    const {redirectHomeTo, redirectMainTo} = useUserNavegate();

    async function login(credentials: AuthCredentials) {
        const user = await post(loginService, credentials);
        saveUser(user);
        redirectHomeTo(user);
    }

    async function logout(user: User) {
        logoutService(user);
        refreshUser();
        redirectMainTo();
    }

    useEffect(() => {
        findUser()
    }, []);

    const closeError = () => refreshError();

    return {user, error, isLoading, login, logout, closeError}
}

export default useAuth;