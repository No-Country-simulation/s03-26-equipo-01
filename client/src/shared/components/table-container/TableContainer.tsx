import {
  Paper,
  Table,
  TableContainer,
} from '@mui/material';
import './table.css';
import BodyTwoAction from './components/body-two-action/BodyTwoAction';
import type { TableContainerProps } from './table-container';
import HeaderTable from './components/header-table/HeaderTable';

const TableEditData = ({
  activeEdit,
  activeDelete,
  tableData,
  customBody,
}: TableContainerProps) => {
  return (
    <div className='table-data-container'>
      <TableContainer component={Paper}>
        <Table>
          <HeaderTable tableData={tableData} />
          {customBody ?? (
            <BodyTwoAction
              activeEdit={activeEdit}
              activeDelete={activeDelete}
              tableData={tableData}
            />
          )}
        </Table>
      </TableContainer>
    </div>
  );
};

export default TableEditData;
