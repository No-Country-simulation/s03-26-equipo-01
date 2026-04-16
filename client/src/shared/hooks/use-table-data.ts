import { useState, useCallback } from 'react';
import type { TablePaginator } from '../types/table/table';
import usePaginator from './use-paginator';

interface UseTableDataProps<T> {
  execute: (page: number, size: number) => Promise<TablePaginator<T>>;
  onError?: (error: unknown) => void;
}

const useTableData = <T>({ execute, onError }: UseTableDataProps<T>) => {
  const { data, page, setPage, fetchData } = usePaginator(execute);
  const [isLoading, setIsLoading] = useState(false);

  const refetch = useCallback(async () => {
    setIsLoading(true);
    try {
      await fetchData();
    } catch (error) {
      onError?.(error);
    } finally {
      setIsLoading(false);
    }
  }, [fetchData, onError]);

  return {
    data,
    page,
    setPage,
    refetch,
    isLoading,
    rows: data?.rows || [],
    headers: data?.headers || [],
    totalElements: data?.totalElements || 0,
    totalPages: data?.totalPages || 0,
    pageSize: data?.size || 5,
  };
};

export default useTableData;
