import type { UserCredentials } from "./user-credentials";


export interface Auth {
    /*
        PROPÓSITO: Logea al usuario con las credenciales dadas.
        RETORNA: 
            * User Admin o Edit (falta modela este tipo)
    */
    login: (user: UserCredentials) => void 
}