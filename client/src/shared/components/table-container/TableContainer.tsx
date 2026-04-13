import {
  Paper,
  Table,
  TableContainer,
} from '@mui/material';
import './table.css';
import HeaderTable from './components/header-table/HeaderTable';
import BodyTwoAction from './components/body-two-action/BodyTwoAction';
import type { TableContainerProps } from './table-container';

const TableEditData = ({
  tableData,
  customBody,
  children
}: TableContainerProps) => {
  return (
    <div className='table-data-container'>
      <TableContainer component={Paper}>
        <Table>
          <HeaderTable tableData={tableData} />
          {customBody ?? (
            <BodyTwoAction
              tableData={tableData}
              children={children}
            />
          )}
        </Table>
      </TableContainer>
    </div>
  );
};

export default TableEditData;
