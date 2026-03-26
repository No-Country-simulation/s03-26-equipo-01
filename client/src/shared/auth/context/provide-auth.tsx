import type { ReactNode } from "react";
import AuthContext from "./create-auth";
import type { Auth } from "../models/auth";
import useAuth from "../hooks/use-auth";


interface AuthProviderProps {
    children: ReactNode
}

const AuthProvider = ({children}: AuthProviderProps) => {

    const {user, error, login} = useAuth();

    const auth: Auth = {
        login: login,
        user,
        error
    }
    
    return (
        <AuthContext.Provider value = {auth}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;