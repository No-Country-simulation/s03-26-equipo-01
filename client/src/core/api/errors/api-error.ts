


export abstract class ApiError extends Error {
    constructor(message: string) {
        super(message);
    }
}