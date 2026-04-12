import { ClientError } from "./client-error";

export class CredentialsError extends ClientError {
    constructor() {
        super('No tienes las credenciales validas.');
    }
}