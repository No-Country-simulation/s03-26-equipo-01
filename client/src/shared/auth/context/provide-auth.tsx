import type { ReactNode } from "react";
import AuthContext from "./create-auth";
import type { Auth } from "../models/auth";
import useAuth from "../hooks/use-auth";


interface AuthProviderProps {
    children: ReactNode
}

const AuthProvider = ({children}: AuthProviderProps) => {

    const {login} = useAuth();

    const auth: Auth = {
        login: login
    }
    
    return (
        <AuthContext.Provider value = {auth}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;