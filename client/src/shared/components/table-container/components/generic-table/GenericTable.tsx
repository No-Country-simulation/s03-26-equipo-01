import { Paper, Table, TableContainer } from '@mui/material';
import type { ReactNode } from 'react';
import '../../table.css';
import HeaderTable from '../header-table/HeaderTable';
import type { TablePaginator } from '../../../../types/table/table';

interface GenericTableProps<T> {
  data: TablePaginator<T>;
  renderBody: ReactNode;
}

const GenericTable = <T,>({ data, renderBody }: GenericTableProps<T>) => {
  const tableData = {
    headers: data.headers,
    rows: [],
  };

  return (
    <div className='table-data-container'>
      <TableContainer component={Paper}>
        <Table>
          <HeaderTable tableData={tableData} />
          {renderBody}
        </Table>
      </TableContainer>
    </div>
  );
};

export default GenericTable;
