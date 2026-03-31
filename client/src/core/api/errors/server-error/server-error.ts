import { ApiError } from "../api-error";

export abstract class ServerError extends ApiError {
    constructor(message: string) {
        super(message);
    }
} 