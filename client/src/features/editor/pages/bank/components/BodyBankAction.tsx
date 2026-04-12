import { TableBody, TableCell, TableRow, Button } from '@mui/material';
import { Video, VideoOff, Image, ImageOff, Inbox } from 'lucide-react';
import type BodyBankActionProps from './BodyBankActionProps';
import TestimonialTags from './testimonial-tags/TestimonialTags';
import MediaIcon from './rows/Media';
import RatingDisplay from './rows/Rating';

const BodyBankAction = ({
  rows,
  onAsoc,
  currentPage,
  pageSize,
}: BodyBankActionProps) => {
  return (
    <TableBody>
      {rows.map((row, index) => (
        <TableRow key={row.id} className={classColor(index)}>
          <TableCell>{currentPage * pageSize + index + 1}</TableCell>
          <TableCell>{row.data.testimonial}</TableCell>

          <TableCell>
            <MediaIcon
              url={row.data.media?.videoUrl}
              IconOn={Video}
              IconOff={VideoOff}
            />
          </TableCell>

          <TableCell>
            <MediaIcon
              url={row.data.media?.imageUrl}
              IconOn={Image}
              IconOff={ImageOff}
            />
          </TableCell>

          <TableCell>
            <TestimonialTags tags={row.data.tags} />
          </TableCell>

          <TableCell>
            <RatingDisplay rating={row.data.rating} />
          </TableCell>

          <TableCell>
            <Button
              variant='outlined'
              size='small'
              startIcon={<Inbox size={16} />}
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
