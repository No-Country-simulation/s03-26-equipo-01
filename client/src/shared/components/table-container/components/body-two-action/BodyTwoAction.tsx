import { TableBody, TableCell, TableRow } from '@mui/material';
import EditButton from '../../../../elements/edit-button/EditButton';
import type { BodyOneActionProps as BodyTwoActionProps } from './body-two-action';
import DeleteButton from '../../../../elements/delete-button/DeleteButton';

const BodyTwoAction = ({
  tableData,
  activeEdit,
  activeDelete,
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

export default BodyTwoAction;
