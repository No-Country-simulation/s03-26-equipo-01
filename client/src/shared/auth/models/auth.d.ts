import type { CredentialsError } from "../../../core/api/errors/client-error/credentials-error";
import type { User } from "../../types/user/user";
import type { UserCredentials } from "./user-credentials";


export interface Auth {
    /*
        PROPÓSITO: Logea al usuario con las credenciales dadas.
        RETORNA: 
            * User Admin o Edit 
    */
    login: (user: UserCredentials) => void 
    closeError: () => void
    user: User | null
    error: CredentialsError | null
}