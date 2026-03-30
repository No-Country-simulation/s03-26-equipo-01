import { ClientError } from "./client-error";

export class CredentialsError extends ClientError {
    constructor() {
        super('El usuario no tiene credenciales validas');
    }
}