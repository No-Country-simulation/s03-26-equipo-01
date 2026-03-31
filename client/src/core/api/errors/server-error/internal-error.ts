import { ServerError } from "./server-error";


export class InternalError extends ServerError {
    constructor() {
        super('No pudimos conectar con el servidor, intenta de nuevo');
    }
} 