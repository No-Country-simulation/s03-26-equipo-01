import { ServerError } from "./server-error";



export class UknowError extends ServerError {
    constructor() {
        super('No pudimos conectar con el servidor, intenta de nuevo');
    }
} 