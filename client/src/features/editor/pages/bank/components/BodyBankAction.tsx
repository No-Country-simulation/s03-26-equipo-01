import {
  TableBody,
  TableCell,
  TableRow,
  Button,
  Typography,
  Box,
} from '@mui/material';
import { Video, VideoOff, Image, ImageOff, Inbox, SearchX } from 'lucide-react';
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
      {rows.length === 0 ? (
        <TableRow>
          <TableCell colSpan={7} align='center' sx={{ py: 8 }}>
            <Box
              sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                gap: 1,
                opacity: 0.5,
              }}
            >
              <SearchX size={48} strokeWidth={1} />
              <Typography variant='body1' fontWeight={500}>
                No se encontraron testimonios disponibles
              </Typography>
              <Typography variant='body2'>
                Todos los testimonios han sido procesados o el banco está vacío.
              </Typography>
            </Box>
          </TableCell>
        </TableRow>
      ) : (
        rows.map((row, index) => (
          <TableRow key={row.id} className={classColor(index)}>
            <TableCell>{currentPage * pageSize + index + 1}</TableCell>
            <TableCell className='truncated-text'>
              {row.data.testimonial}
            </TableCell>

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
        ))
      )}
    </TableBody>
  );
};

const classColor = (index: number): string =>
  index % 2 === 0 ? 'clean-color' : 'dark-color';

export default BodyBankAction;
