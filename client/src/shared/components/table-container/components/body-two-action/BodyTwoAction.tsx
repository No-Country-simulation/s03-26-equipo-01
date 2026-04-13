import { TableBody, TableCell, TableRow } from '@mui/material';
import type {BodyOneActionProps as BodyTwoActionProps } from './body-two-action';

const BodyTwoAction = ({
  tableData,
  children
}: BodyTwoActionProps) => {
  return (
    <TableBody>
      {tableData.rows.map((row) => (
        <TableRow
          key={row.id}
          className={classColor(tableData.rows.indexOf(row))}
        >
          {row.fields.map((field) => (
            <TableCell key={field}>
              {field}
            </TableCell>
          ))}
          <TableCell
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              maxWidth: '140px',
            }}
          >
            {children(row.id)}
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  );
};

const classColor = (index: number): string =>
  index % 2 === 0 ? 'clean-color' : 'dark-color';

export default BodyTwoAction;
