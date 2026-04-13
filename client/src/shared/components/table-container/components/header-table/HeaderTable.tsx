import { TableCell, TableHead, TableRow } from "@mui/material";
import type { HeaderTableProps } from "./header-table";

const HeaderTable = ({ tableData }: HeaderTableProps) => {
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

export default HeaderTable;