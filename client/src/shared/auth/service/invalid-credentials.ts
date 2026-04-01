import { AxiosError } from "axios";
import { CredentialsError } from "../../../core/api/errors/client-error/credentials-error";
import type { ApiError } from "../../../core/api/errors/api-error";

function handleInvalidCredentials(error: unknown): ApiError | unknown {
    if (error instanceof AxiosError) {
         if(error.status === 404 || error.status === 401) {
            return new CredentialsError();
        }
    }
    return error;
}

export default handleInvalidCredentials;