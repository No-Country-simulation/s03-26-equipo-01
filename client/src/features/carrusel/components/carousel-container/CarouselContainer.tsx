import { useState, useRef, useEffect } from 'react';
import type { CarouselTestimonial } from '../../models/carouselTestimonial';
import CarouselCard from '../carousel-card/CarouselCard';
import { ChevronLeft, ChevronRight } from 'lucide-react';
import './carousel-container.css';

interface CarouselContainerProps {
  testimonials: CarouselTestimonial[];
  autoPlay?: boolean;
  autoPlayInterval?: number;
}

const CarouselContainer = ({
  testimonials,
  autoPlay = false,
  autoPlayInterval = 5000,
}: CarouselContainerProps) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [isTransitioning, setIsTransitioning] = useState(false);
  const carouselRef = useRef<HTMLDivElement>(null);
  const autoPlayIntervalRef = useRef<NodeJS.Timeout | null>(null);

  const slideCount = testimonials.length;

  // Auto-play logic
  useEffect(() => {
    if (autoPlay && slideCount > 1) {
      autoPlayIntervalRef.current = setInterval(() => {
        handleNext();
      }, autoPlayInterval);

      return () => {
        if (autoPlayIntervalRef.current) {
          clearInterval(autoPlayIntervalRef.current);
        }
      };
    }
  }, [autoPlay, autoPlayInterval, slideCount]);

  // Detener auto-play cuando el usuario interactúa
  const stopAutoPlay = () => {
    if (autoPlayIntervalRef.current) {
      clearInterval(autoPlayIntervalRef.current);
    }
  };

  const handleNext = () => {
    if (!isTransitioning) {
      stopAutoPlay();
      setIsTransitioning(true);
      setCurrentIndex((prev) => (prev + 1) % slideCount);
      setTimeout(() => setIsTransitioning(false), 300);
    }
  };

  const handlePrev = () => {
    if (!isTransitioning) {
      stopAutoPlay();
      setIsTransitioning(true);
      setCurrentIndex((prev) => (prev - 1 + slideCount) % slideCount);
      setTimeout(() => setIsTransitioning(false), 300);
    }
  };

  const handleDotClick = (index: number) => {
    if (!isTransitioning && index !== currentIndex) {
      stopAutoPlay();
      setIsTransitioning(true);
      setCurrentIndex(index);
      setTimeout(() => setIsTransitioning(false), 300);
    }
  };

  if (slideCount === 0) {
    return (
      <div className='carousel-empty'>
        <p>No hay testimonios disponibles</p>
      </div>
    );
  }

  return (
    <div className='carousel-wrapper'>
      <div className='carousel-container' ref={carouselRef}>
        {/* Pista de carrusel */}
        <div
          className='carousel-track'
          style={{
            transform: `translateX(calc(-${currentIndex} * (100% + 1rem)))`,
          }}
        >
          {testimonials.map((testimonial) => (
            <div key={testimonial.id} className='carousel-slide'>
              <CarouselCard testimonial={testimonial} />
            </div>
          ))}
        </div>

        {/* Botones de navegación */}
        {slideCount > 1 && (
          <>
            <button
              className='carousel-button carousel-button-prev'
              onClick={handlePrev}
              aria-label='Testimonio anterior'
              disabled={isTransitioning}
            >
              <ChevronLeft size={24} />
            </button>
            <button
              className='carousel-button carousel-button-next'
              onClick={handleNext}
              aria-label='Siguiente testimonio'
              disabled={isTransitioning}
            >
              <ChevronRight size={24} />
            </button>
          </>
        )}
      </div>

      {/* Indicadores de puntos */}
      {slideCount > 1 && (
        <div className='carousel-indicators'>
          {testimonials.map((_, index) => (
            <button
              key={index}
              className={`carousel-dot ${index === currentIndex ? 'active' : ''}`}
              onClick={() => handleDotClick(index)}
              aria-label={`Ir al testimonio ${index + 1}`}
              disabled={isTransitioning}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default CarouselContainer;
