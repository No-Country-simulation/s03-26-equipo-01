import {
  Box,
  Typography,
  RadioGroup,
  FormControlLabel,
  Radio,
} from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../../models/testimonial-response';

interface MediaItemProps {
  label: string;
  hasMedia: boolean;
  show: boolean;
  mediaUrl?: string;
  thumbnailUrl?: string;
  placeholderText: string;
  onChange: (value: string) => void;
}

const thumbnailBoxStyle = {
  width: 140,
  height: 140,
  bgcolor: '#f9f9f9',
  border: '1px solid #eee',
  borderRadius: 1,
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  overflow: 'hidden',
};

const imageStyle = {
  width: '100%',
  height: '100%',
  objectFit: 'cover' as const,
};

export const MediaItem = ({
  label,
  hasMedia,
  show,
  mediaUrl,
  thumbnailUrl,
  placeholderText,
  onChange,
}: MediaItemProps) => {
  return (
    <Box display='grid' gridTemplateColumns='1fr 140px' gap={2}>
      <Box>
        <Typography variant='body2' mb={1}>
          {label}
        </Typography>

        <RadioGroup
          value={show ? 'si' : 'no'}
          onChange={(e) => onChange(e.target.value)}
        >
          <FormControlLabel
            value='si'
            control={<Radio size='small' />}
            label='Sí'
            disabled={!hasMedia}
          />
          <FormControlLabel
            value='no'
            control={<Radio size='small' />}
            label='No'
          />
        </RadioGroup>

        {mediaUrl && (
          <Typography
            variant='caption'
            color='primary'
            component='a'
            href={mediaUrl}
            target='_blank'
            sx={{
              textDecoration: 'none',
              '&:hover': { textDecoration: 'underline' },
            }}
          >
            {mediaUrl}
          </Typography>
        )}
      </Box>

      <Box sx={thumbnailBoxStyle}>
        {thumbnailUrl ? (
          <img src={thumbnailUrl} alt={placeholderText} style={imageStyle} />
        ) : (
          <Typography variant='caption' color='text.secondary' align='center'>
            {placeholderText}
          </Typography>
        )}
      </Box>
    </Box>
  );
};
