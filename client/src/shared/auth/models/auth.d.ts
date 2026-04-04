import type { CredentialsError } from "../../../core/api/errors/client-error/credentials-error";
import type { User } from "../../user/models/user";
import type { AuthCredentials } from "./user-credentials";


export interface Auth {
    login: (user: AuthCredentials) => void 
    closeError: () => void
    user: User | null
    error: CredentialsError | null
}