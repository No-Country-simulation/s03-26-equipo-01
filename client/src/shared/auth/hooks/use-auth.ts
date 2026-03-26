import { useState } from "react";
import type { UserCredentials } from "../models/user-credentials";
import type { User } from "../../types/user/user";
import loginService from "../service/login.service";
import useUserNavegate from "./use-user-navegate";

const useAuth = () => {
    
    const [user, setUser] = useState<User | null>(null);
    const [error, setError] = useState<Error | null>(null);
    const {redirectTo} = useUserNavegate();

    async function login(credentials: UserCredentials) {
        try {
            const user = await loginService(credentials);
            setUser(user);
            redirectTo(user);
        }
        catch(error: unknown) {
            setError(error as Error);
        }
    }

    return {user, error, login}
}

export default useAuth;