import { useState } from "react";
import { ApiError } from "../errors/api-error";

const useApi = <T extends object>() => {

    const [error, setError] = useState<ApiError | null>(null);

    async function post<S>(execute: (newData: S) => Promise<T>, data: S): Promise<T> {
        try {
            return await execute(data);
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
    }

    async function get<T>(execute: () => Promise<T>): Promise<T> {
        try {
            return await execute();
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
    }

    const refreshError = () => setError(null);

    return {get, post, error, refreshError}
}

export default useApi;