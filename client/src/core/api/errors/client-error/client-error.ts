

export abstract class ClientError extends Error {
    constructor(message: string) {
        super(message);
    }
}