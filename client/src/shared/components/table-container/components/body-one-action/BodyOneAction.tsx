import { TableBody, TableCell, TableRow } from '@mui/material';
import EditButton from '../../../../elements/edit-button/EditButton';
import type { BodyOneActionProps } from './body-one-action';
import DeleteButton from '../../../../elements/delete-button/DeleteButton';

const BodyOneAction = ({
  tableData,
  activeEdit,
  activeDelete,
}: BodyOneActionProps) => {
  return (
    <TableBody>
      {tableData.rows.map((row) => (
        <TableRow
          key={tableData.rows.indexOf(row)}
          className={classColor(tableData.rows.indexOf(row))}
        >
          {row.fields.map((field) => (
            <TableCell key={row.fields.indexOf(field)}>
              {field.toString()}
            </TableCell>
          ))}
          <TableCell
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              maxWidth: '140px',
            }}
          >
            <DeleteButton onSubmit={activeDelete} id={row.id} />
            <EditButton onSubmit={activeEdit} id={row.id} />
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  );
};

const classColor = (index: number): string =>
  index % 2 === 0 ? 'clean-color' : 'dark-color';

export default BodyOneAction;
