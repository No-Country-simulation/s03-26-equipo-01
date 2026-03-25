import { useState } from "react";
import type { UserCredentials } from "../types/user-credentials";
import type { User } from "../../types/user";

const useAuth = () => {
    const [user, setUser] = useState<User | null>(null);

    function login(credentials: UserCredentials) {
        console.log(credentials);
        setUser(null);
    }

    return {user, login}
}

export default useAuth;