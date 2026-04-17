import { TableBody, TableCell, TableRow, Typography, Box } from '@mui/material';
import { Video, VideoOff, Image, ImageOff, SearchX } from 'lucide-react';

import StatusBadge from '../../../status-badge/StatusBadge';
import type { BodyTestimonyProps, TestimonialData } from './body-testimony';
import type { DataRow } from '../../../../types/table/table';
import MediaIcon from '../../../table-cells/media-icon/MediaIcon';
import TestimonialTags from '../../../table-cells/testimonial-tags/TestimonialTags';
import RatingDisplay from '../../../table-cells/rating-display/RatingDisplay';

const BodyTestimony = <T extends TestimonialData = TestimonialData>({
  rows,
  currentPage,
  pageSize,
  columns,
}: BodyTestimonyProps<T>) => {
  return (
    <TableBody>
      {rows.length === 0 ? (
        <TableRow>
          <TableCell colSpan={columns.length} align='center' sx={{ py: 8 }}>
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
                El banco está vacío o todos han sido procesados.
              </Typography>
            </Box>
          </TableCell>
        </TableRow>
      ) : (
        rows.map((row, index) => (
          <TestimonyRow
            key={row.id}
            row={row}
            index={index}
            currentPage={currentPage}
            pageSize={pageSize}
            columns={columns}
          />
        ))
      )}
    </TableBody>
  );
};

interface TestimonyRowProps<T extends TestimonialData = TestimonialData> {
  row: DataRow<T>;
  index: number;
  currentPage: number;
  pageSize: number;
  columns: string[];
}

const TestimonyRow = <T extends TestimonialData = TestimonialData>({
  row,
  index,
  currentPage,
  pageSize,
  columns,
}: TestimonyRowProps<T>) => {
  const rowNumber = currentPage * pageSize + index + 1;
  const classColor = (idx: number) => (idx % 2 === 0 ? 'even-row' : 'odd-row');

  return (
    <TableRow className={classColor(index)}>
      <TableCell>{rowNumber}</TableCell>
      <TableCell className='truncated-text'>{row.data.testimonial}</TableCell>

      {columns.includes('VIDEO') && (
        <TableCell>
          <MediaIcon
            url={
              row.data.media?.videoUrl && row.data.media.videoUrl.trim()
                ? row.data.media.videoUrl
                : undefined
            }
            IconOn={Video}
            IconOff={VideoOff}
          />
        </TableCell>
      )}

      {columns.includes('IMAGEN') && (
        <TableCell>
          <MediaIcon
            url={
              row.data.media?.imageUrl && row.data.media.imageUrl.trim()
                ? row.data.media.imageUrl
                : row.data.media?.thumbnailUrl &&
                    row.data.media.thumbnailUrl.trim()
                  ? row.data.media.thumbnailUrl
                  : undefined
            }
            IconOn={Image}
            IconOff={ImageOff}
          />
        </TableCell>
      )}

      {columns.includes('TAGS') && (
        <TableCell>
          <TestimonialTags tags={row.data.tags || []} />
        </TableCell>
      )}

      {columns.includes('CALIFICACIÓN') && (
        <TableCell>
          <RatingDisplay rating={row.data.rating || 0} />
        </TableCell>
      )}

      {columns.includes('CATEGORIA') && (
        <TableCell>{row.data.category?.name || '-'}</TableCell>
      )}

      {columns.includes('ESTADO') && (
        <TableCell>
          <StatusBadge
            status={
              row.data.stateTestimonial || row.data.state || 'DESCONOCIDO'
            }
          />
        </TableCell>
      )}

      {row.actions && <TableCell align='right'>{row.actions}</TableCell>}
    </TableRow>
  );
};

export default BodyTestimony;
