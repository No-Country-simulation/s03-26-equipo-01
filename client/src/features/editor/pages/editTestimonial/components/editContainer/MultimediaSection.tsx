import { Box, Typography } from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import { useMultimediaToggle } from './hooks/use-multimedia-toggle';
import { MediaItem } from './components/MediaItem';

interface MultimediaSectionProps {
  media: TestimonialResponseDTO['media'];
  showVideo: boolean;
  showImage: boolean;
  onVideoChange: (val: boolean) => void;
  onImageChange: (val: boolean) => void;
}

export const MultimediaSection = ({
  media,
  showVideo,
  showImage,
  onVideoChange,
  onImageChange,
}: MultimediaSectionProps) => {
  const hasVideo = !!media?.videoUrl;
  const hasImage = !!media?.imageUrl;

  const { handleVideoToggle, handleImageToggle } = useMultimediaToggle(
    onVideoChange,
    onImageChange,
  );

  return (
    <Box>
      <Typography variant='subtitle2' fontWeight={600} mb={3}>
        4.- Multimedia
      </Typography>

      <Box mb={4}>
        <MediaItem
          label='Mostrar video en el testimonio'
          hasMedia={hasVideo}
          show={showVideo}
          mediaUrl={media?.videoUrl}
          thumbnailUrl={media?.thumbnailUrl}
          placeholderText='Miniatura de video'
          onChange={handleVideoToggle}
        />
      </Box>

      <Box>
        <MediaItem
          label='Mostrar imagen en el testimonio'
          hasMedia={hasImage}
          show={showImage}
          mediaUrl={undefined}
          thumbnailUrl={media?.imageUrl}
          placeholderText='Miniatura de imagen'
          onChange={handleImageToggle}
        />
      </Box>
    </Box>
  );
};
