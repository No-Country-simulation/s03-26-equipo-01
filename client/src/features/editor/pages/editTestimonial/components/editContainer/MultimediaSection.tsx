import {
  Box,
  Typography,
  RadioGroup,
  FormControlLabel,
  Radio,
} from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';

interface MultimediaSectionProps {
  media: TestimonialResponseDTO['media'];
  showVideo: boolean;
  showImage: boolean;
  onVideoChange: (val: boolean) => void;
  onImageChange: (val: boolean) => void;
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

export const MultimediaSection = ({
  media,
  showVideo,
  showImage,
  onVideoChange,
  onImageChange,
}: MultimediaSectionProps) => {
  const hasVideo = !!media?.videoUrl;
  const hasImage = !!media?.imageUrl;

  const handleVideoToggle = (value: string) => {
    const isYes = value === 'si';
    onVideoChange(isYes);
    if (isYes) onImageChange(false);
  };

  const handleImageToggle = (value: string) => {
    const isYes = value === 'si';
    onImageChange(isYes);
    if (isYes) onVideoChange(false);
  };

  return (
    <Box>
      <Typography variant='subtitle2' fontWeight={600} mb={3}>
        4.- Multimedia
      </Typography>

      <Box display='grid' gridTemplateColumns='1fr 140px' gap={2} mb={4}>
        <Box>
          <Typography variant='body2' mb={1}>
            Mostrar video en el testimonio
          </Typography>

          <RadioGroup
            value={showVideo ? 'si' : 'no'}
            onChange={(e) => handleVideoToggle(e.target.value)}
          >
            <FormControlLabel
              value='si'
              control={<Radio size='small' />}
              label='Sí'
              disabled={!hasVideo}
            />
            <FormControlLabel
              value='no'
              control={<Radio size='small' />}
              label='No'
            />
          </RadioGroup>

          {media?.videoUrl && (
            <Typography
              variant='caption'
              color='primary'
              component='a'
              href={media.videoUrl}
              target='_blank'
              sx={{
                textDecoration: 'none',
                '&:hover': { textDecoration: 'underline' },
              }}
            >
              {media.videoUrl}
            </Typography>
          )}
        </Box>

        <Box sx={thumbnailBoxStyle}>
          {media?.thumbnailUrl ? (
            <img
              src={media.thumbnailUrl}
              alt='Video thumbnail'
              style={imageStyle}
            />
          ) : (
            <Typography variant='caption' color='text.secondary' align='center'>
              Miniatura de video
            </Typography>
          )}
        </Box>
      </Box>

      <Box display='grid' gridTemplateColumns='1fr 140px' gap={2}>
        <Box>
          <Typography variant='body2' mb={1}>
            Mostrar imagen en el testimonio
          </Typography>

          <RadioGroup
            value={showImage ? 'si' : 'no'}
            onChange={(e) => handleImageToggle(e.target.value)}
          >
            <FormControlLabel
              value='si'
              control={<Radio size='small' />}
              label='Sí'
              disabled={!hasImage}
            />
            <FormControlLabel
              value='no'
              control={<Radio size='small' />}
              label='No'
            />
          </RadioGroup>
        </Box>

        <Box sx={thumbnailBoxStyle}>
          {media?.imageUrl ? (
            <img
              src={media.imageUrl}
              alt='Image thumbnail'
              style={imageStyle}
            />
          ) : (
            <Typography variant='caption' color='text.secondary' align='center'>
              Miniatura de imagen
            </Typography>
          )}
        </Box>
      </Box>
    </Box>
  );
};
