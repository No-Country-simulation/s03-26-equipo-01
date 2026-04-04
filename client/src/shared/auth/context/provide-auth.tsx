import type { ReactNode } from "react";
import AuthContext from "./create-auth";
import type { Auth } from "../models/auth";
import useAuth from "../hooks/use-auth";


interface AuthProviderProps {
    children: ReactNode
}

const AuthProvider = ({children}: AuthProviderProps) => {

    const {closeError, user, error, login, logout} = useAuth();

    const auth: Auth = {
        closeError,
        login,
        logout,
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