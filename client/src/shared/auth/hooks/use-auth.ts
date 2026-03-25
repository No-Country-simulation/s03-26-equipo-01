import { useState } from "react";
import type { UserCredentials } from "../types/user-credentials";

interface User {
    name: string
}

const useAuth = () => {
    const [user, setUser] = useState<User | null>(null);

    function login(credentials: UserCredentials) {
        console.log(credentials);
        setUser(null);
    }

    return {user, login}
}

export default useAuth;