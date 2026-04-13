import {
  Paper,
  Table,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from '@mui/material';
import './table.css';
import BodyTwoAction from './components/body-one-action/BodyOneAction';
import type { HeaderProps, TableContainerProps } from './table-container';

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
          <Header tableData={tableData} />
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

const Header = ({ tableData }: HeaderProps) => {
  return (
    <TableHead className='table-header'>
      <TableRow>
        {tableData.headers.map((header) => (
          <TableCell key={header} className='table-border'>
            {header}
          </TableCell>
        ))}
        <TableCell className='table-border'>ACCIONES</TableCell>
      </TableRow>
    </TableHead>
  );
};

export default TableEditData;
