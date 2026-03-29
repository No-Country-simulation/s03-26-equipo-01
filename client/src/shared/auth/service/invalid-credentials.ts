import { AxiosError } from "axios";
import { CredentialsError } from "../../../core/api/errors/client-error/credentials-error";


function handleInvalidCredentials(error: unknown): never {
    if (error instanceof AxiosError) {
         if(error.status === 404 || error.status === 401) {
            throw new CredentialsError();
        }
    }
    throw error;
    
}

export default handleInvalidCredentials;