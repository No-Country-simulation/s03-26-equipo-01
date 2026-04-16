import RatingDisplay from '../../../editor/pages/bank/components/rows/Rating';
import TestimonialTags from '../../../editor/pages/bank/components/testimonial-tags/TestimonialTags';
import type { CarouselTestimonial } from '../../models/carouselTestimonial';

import './carousel-card.css';
import { Play } from 'lucide-react';

interface CarouselCardProps {
  testimonial: CarouselTestimonial;
}

const CarouselCard = ({ testimonial }: CarouselCardProps) => {
  const hasMedia = testimonial.youtubeUrl || testimonial.image;
  const youtubeHref = testimonial.videoId
    ? `https://www.youtube.com/watch?v=${testimonial.videoId}`
    : testimonial.youtubeUrl;

  return (
    <article className='carousel-card'>
      {/* Header con nombre y rating */}
      <div className='carousel-card-header'>
        <h3 className='carousel-card-name'>{testimonial.fullName}</h3>
        <div className='carousel-card-rating'>
          <RatingDisplay rating={testimonial.rating} />
        </div>
      </div>

      {/* Descripción del testimonio */}
      <p className='carousel-card-description'>{testimonial.testimonial}</p>

      {/* Tags */}
      {testimonial.tags && testimonial.tags.length > 0 && (
        <div className='carousel-card-tags-wrapper'>
          <TestimonialTags tags={testimonial.tags} />
        </div>
      )}

      {/* Contenedor de multimedia */}
      {hasMedia && (
        <div className='carousel-card-media-container'>
          {testimonial.youtubeUrl ? (
            <div className='carousel-card-video-wrapper'>
              {testimonial.youtubeThumbnail ? (
                <a
                  href={youtubeHref}
                  target='_blank'
                  rel='noreferrer'
                  className='carousel-card-video-link'
                  aria-label={`Ver video de ${testimonial.fullName} en YouTube`}
                >
                  <img
                    src={testimonial.youtubeThumbnail}
                    alt={`Miniatura del video de ${testimonial.fullName}`}
                    className='carousel-card-image'
                  />
                  <span className='carousel-card-video-overlay'>
                    <Play size={32} color='#ffffff' fill='#ffffff' />
                  </span>
                </a>
              ) : testimonial.videoId ? (
                <iframe
                  className='carousel-card-video'
                  src={`https://www.youtube.com/embed/${testimonial.videoId}`}
                  title={`Testimonio de ${testimonial.fullName}`}
                  allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture'
                  allowFullScreen
                />
              ) : (
                <div className='carousel-card-video-placeholder'>
                  <Play size={32} color='#FF9B71' fill='#FF9B71' />
                </div>
              )}
            </div>
          ) : testimonial.image ? (
            <div className='carousel-card-image-wrapper'>
              <img
                src={testimonial.image}
                alt={testimonial.fullName}
                className='carousel-card-image'
              />
            </div>
          ) : null}
        </div>
      )}
    </article>
  );
};

export default CarouselCard;
