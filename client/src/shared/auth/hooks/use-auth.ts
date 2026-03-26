import { useState } from "react";
import type { UserCredentials } from "../types/user-credentials";
import type { User } from "../../types/user/user";
import loginService from "../service/login.service";

const useAuth = () => {
    
    const [user, setUser] = useState<User | null>(null);
    const [error, setError] = useState<Error | null>(null);

    async function login(credentials: UserCredentials) {
        try {
            setUser(await loginService(credentials))
        }
        catch(error: unknown) {
            setError(error as Error);
        }
    }

    return {user, error, login}
}

export default useAuth;