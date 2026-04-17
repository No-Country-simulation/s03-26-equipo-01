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

    async function put<S>(execute: (newData: S, id?: number) => Promise<T>, data: S, id?: number): Promise<T> {
        try {
            setIsLoading(true);
            const newData = await execute(data, id);
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

    async function patch(execute: (id: number) => Promise<void>, id: number): Promise<void> {
        try {
            setIsLoading(true);
            await execute(id);
            refreshError();
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
        finally {
            setIsLoading(false)
        }
    }

    async function patchWithBody<T>(execute: (id: number) => Promise<T>, id: number): Promise<T> {
        try {
            setIsLoading(true);
            const newData = await execute(id);
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

    async function deleted(execute: (id: number) => Promise<void>, id: number): Promise<void> {
        try {
            setIsLoading(true);
            await execute(id);
            refreshError();
        } 
        catch (error) {
            if (error instanceof ApiError) setError(error);
            throw error;
        }
        finally {
            setIsLoading(false)
        }
    }

    async function deletedWithBody<T>(execute: (id: number) => Promise<T>, id: number): Promise<T> {
        try {
            setIsLoading(true);
            const data = await execute(id);
            refreshError();
            return data;
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

    return {get, post, put, patch, patchWithBody, deleted, deletedWithBody, error, refreshError, isLoading}
}

export default useApi;