import AuthContext from "./create-auth";
import type { Auth } from "../models/auth";
import useAuth from "../hooks/use-auth";
import type { ReactNode } from "react";


interface AuthProviderProps {
    children: ReactNode
}

const AuthProvider = ({children}: AuthProviderProps) => {

    const {user, error, isLoading, login, logout, closeError} = useAuth()

    const auth: Auth = {
        closeError,
        login,
        logout,
        user,
        isLoading,
        error
    }
    
    return (
        <AuthContext.Provider value = {auth}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;