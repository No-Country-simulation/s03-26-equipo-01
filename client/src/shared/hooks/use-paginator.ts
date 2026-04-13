import { useEffect, useState } from 'react';
import type { TablePaginator } from '../types/table/table';
import useApi from '../../core/api/hooks/use-api';

const usePaginator = <T>(
  execute: (page: number, size: number) => Promise<TablePaginator<T>>,
) => {
  const [data, setData] = useState<TablePaginator<T>>();
  const [page, setPage] = useState(0);
  const [size] = useState(5);
  const { get } = useApi();

  const fetchData = () => {
    get<TablePaginator<T>>(() => execute(page, size))
      .then((data) => setData(data))
      .catch(console.error);
  };

  useEffect(() => {
    fetchData();
  }, [page]);

  const addRow = (row: T, id: number) =>
    data?.rows.push({
      id: id,
      data: row,
    });

  return { data, page, setPage, addRow, fetchData };
};

export default usePaginator;
