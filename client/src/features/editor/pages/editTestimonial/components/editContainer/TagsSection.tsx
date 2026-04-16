import {
  Box,
  Typography,
  Chip,
  Autocomplete,
  TextField,
  CircularProgress,
} from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import type { Tag } from '../../../../../admin/models/tag';
import { useTagsSearch } from '../../hooks/use-TagsSearch';

interface TagsSectionProps {
  tags: TestimonialResponseDTO['tags'];
  onRemoveTag: (tagId: number) => void;
  onAddTag: (tag: Tag) => void;
  testimonialId: number;
}

export const TagsSection = ({
  tags,
  onRemoveTag,
  onAddTag,
  testimonialId,
}: TagsSectionProps) => {
  const { tagOptions, loadingSearch, searchByName } =
    useTagsSearch(testimonialId);

  return (
    <Box>
      <Typography variant='subtitle2' fontWeight={600} mb={1}>
        2.- Etiquetas
      </Typography>

      <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 1, mb: 2 }}>
        {tags &&
          tags.map((tag) => (
            <Chip
              key={tag.id}
              label={tag.name}
              onDelete={() => onRemoveTag(tag.id)}
              size='small'
              variant='outlined'
              sx={{
                color: '#1a237e',
                borderColor: '#1a237e',
                borderRadius: '12px',
                backgroundColor: '#f5f6fa',
                height: '26px',
                '& .MuiChip-label': { fontSize: '12px', fontWeight: 500 },
                '& .MuiTouchRipple-root': { display: 'none' },
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

      <Autocomplete
        options={tagOptions.filter(
          (opt) => !tags?.some((t) => t.id === opt.id),
        )}
        getOptionLabel={(option) => option.name}
        value={null}
        onChange={(_, newValue) => {
          if (newValue) onAddTag(newValue);
        }}
        onFocus={() => searchByName('')}
        onInputChange={(_, value) => searchByName(value)}
        loading={loadingSearch}
        renderInput={(params) => (
          <TextField
            {...params}
            label='Añadir tag'
            variant='standard'
            InputProps={{
              ...params.InputProps,
              endAdornment: (
                <>
                  {loadingSearch && <CircularProgress size={16} />}
                  {params.InputProps.endAdornment}
                </>
              ),
            }}
          />
        )}
      />
    </Box>
  );
};
