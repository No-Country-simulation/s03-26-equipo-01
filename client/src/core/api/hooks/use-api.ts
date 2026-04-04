import { useState } from "react";
import { ApiError } from "../errors/api-error";

const useApi = <T extends object>() => {

    const [error, setError] = useState<ApiError | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    async function post<S>(execute: (newData: S) => Promise<T>, data: S): Promise<T> {
        try {
            setIsLoading(true);
            const newData = await execute(data);
            refreshError();
            return newData;
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
        finally {
            setIsLoading(false)
        }
    }

    async function get<T>(execute: () => Promise<T>): Promise<T> {
        try {
            setIsLoading(true);
            const newData = await execute();
            refreshError();
            return newData;
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
        finally {
            setIsLoading(false)
        }
    }

    const refreshError = () => setError(null);

    return {get, post, error, refreshError, isLoading}
}

export default useApi;