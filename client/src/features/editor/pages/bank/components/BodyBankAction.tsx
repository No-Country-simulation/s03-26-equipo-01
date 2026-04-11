import { TableBody, TableCell, TableRow, Button } from '@mui/material';
import type { TestimonialSimpleDTO } from './testimonial';
import type { Row } from './table';

interface BodyBankActionProps {
  rows: Row<TestimonialSimpleDTO>[];
  onAsoc: (id: number) => void;
}

const BodyBankAction = ({ rows, onAsoc }: BodyBankActionProps) => {
  return (
    <TableBody>
      {rows.map((row, index) => (
        <TableRow key={row.id} className={classColor(index)}>
          <TableCell>{index + 1}</TableCell>
          <TableCell>{row.data.testimonial}</TableCell>
          <TableCell>{row.data.media?.videoUrl ? '✅' : '❌'}</TableCell>
          <TableCell>{row.data.media?.thumbnailUrl ? '✅' : '❌'}</TableCell>
          <TableCell>{row.data.tags.map((t) => t.name).join(', ')}</TableCell>
          <TableCell>{row.data.rating}</TableCell>
          <TableCell>
            <Button
              variant='outlined'
              size='small'
              onClick={() => onAsoc(row.id)}
            >
              Pasar a trabajo
            </Button>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  );
};

const classColor = (index: number): string =>
  index % 2 === 0 ? 'clean-color' : 'dark-color';

export default BodyBankAction;
