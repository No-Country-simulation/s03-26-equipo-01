import { useState } from "react";
import { ApiError } from "../errors/api-error";

const useApi = <T extends object>() => {

    const [error, setError] = useState<ApiError | null>(null);

    async function post<S>(execute: (newData: S) => Promise<T>, data: S): Promise<T> {
        try {
            return await execute(data);
        } 
        catch (err) {
            if (err instanceof ApiError) setError(err);
            throw err;
        }
    }

    const refreshError = () => setError(null);

    return {post, error, refreshError}
}

export default useApi;