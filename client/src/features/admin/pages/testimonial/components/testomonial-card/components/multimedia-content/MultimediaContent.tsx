import { ImageOff } from 'lucide-react';
import type { MultimediaContentProps } from './multimedia-content';
import './styles/testimonial.css';

const MultimediaContent = ({ testimonial }: MultimediaContentProps) => {
  const imageUrl = testimonial.media?.imageUrl?.trim();
  const videoUrl = testimonial.media?.videoUrl?.trim();
  const thumbnailUrl = testimonial.media?.thumbnailUrl?.trim();

  const hasImage = Boolean(imageUrl);
  const hasVideo = Boolean(videoUrl && thumbnailUrl);

  return (
    <section className='testimonial-admin-media'>
      <p>Contenido multimedia</p>

      {hasImage ? (
        <div className='testimonial-admin-media_preview'>
          <img
            src={imageUrl}
            alt='Imagen adjunta del testimonio'
            className='testimonial-admin-media_image'
          />
        </div>
      ) : hasVideo ? (
        <a
          href={videoUrl}
          className='testimonial-admin-media_link'
          target='_blank'
          rel='noreferrer'
          aria-label='Abrir video del testimonio'
        >
          <img
            src={thumbnailUrl}
            alt='Miniatura del video del testimonio'
            className='testimonial-admin-media_image'
          />
          <span className='play-button' aria-hidden='true'></span>
        </a>
      ) : (
        <div className='testimonial-admin-media_placeholder'>
          <ImageOff size={36} strokeWidth={1.75} />
          <span>No hay contenido multimedia</span>
        </div>
      )}
    </section>
  );
};

export default MultimediaContent;
