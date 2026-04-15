import {
  Box,
  Typography,
  Chip,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';

interface TagsSectionProps {
  tags: TestimonialResponseDTO['tags'];
  onRemoveTag: (tagId: number) => void; // 👈
}

export const TagsSection = ({ tags, onRemoveTag }: TagsSectionProps) => (
  <Box>
    <Typography variant='subtitle2' fontWeight={600} mb={1}>
      2.- Etiquetas
    </Typography>

    <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 1, mb: 2 }}>
      {tags.map((tag) => (
        <Chip
          key={tag.id}
          label={tag.name}
          onDelete={() => onRemoveTag(tag.id)} // 👈
          size='small'
          variant='outlined'
          sx={{
            color: '#1a237e',
            borderColor: '#1a237e',
            borderRadius: '12px',
            backgroundColor: '#f5f6fa',
            height: '26px',

            '& .MuiChip-label': {
              fontSize: '12px',
              fontWeight: 500,
            },

            '& .MuiTouchRipple-root': {
              display: 'none',
            },

            '& .MuiChip-deleteIcon': {
              color: '#1a237e',
              margin: '0 4px 0 -4px',
              fontSize: '16px',
              opacity: 0.7,
              transition: 'all 0.2s ease',

              '&:hover': {
                backgroundColor: 'transparent',
                opacity: 1,
                transform: 'scale(1.1)',
              },

              '&:focus, &:active, &:focus-visible': {
                backgroundColor: 'transparent',
                outline: 'none',
              },
            },
          }}
        />
      ))}
    </Box>

    <FormControl fullWidth variant='standard'>
      <InputLabel>Añadir tag</InputLabel>
      <Select value=''>
        <MenuItem value=''>Añadir tag</MenuItem>
      </Select>
    </FormControl>
  </Box>
);
