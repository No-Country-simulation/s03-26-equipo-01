import { ApiError } from "../api-error";


export abstract class ClientError extends ApiError {
    constructor(message: string) {
        super(message);
    }
}