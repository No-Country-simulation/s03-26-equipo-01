import { useContext } from "react"
import AuthContext from "./create-auth"

const useAuthContext = () => {
    const context = useContext(AuthContext);

    if(!context) throw new Error("No hay contexto para iniciar sesión");

    return context;
}

export default useAuthContext;