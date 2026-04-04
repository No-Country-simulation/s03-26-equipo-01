import { ClientError } from "./client-error";


export class NotFoundError extends ClientError {
    constructor() {
        super("No se pudo encontrar")
    }
}