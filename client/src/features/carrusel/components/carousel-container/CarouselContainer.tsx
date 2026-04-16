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
  const [slideMetrics, setSlideMetrics] = useState({
    visibleSlides: 1,
    slideWidth: 0,
    gap: 16,
  });
  const carouselRef = useRef<HTMLDivElement>(null);
  const autoPlayIntervalRef = useRef<NodeJS.Timeout | null>(null);

  const slideCount = testimonials.length;
  const canNavigate = slideCount > slideMetrics.visibleSlides;
  const maxIndex = Math.max(slideCount - slideMetrics.visibleSlides, 0);

  useEffect(() => {
    const updateSlideMetrics = () => {
      if (!carouselRef.current) {
        return;
      }

      const firstSlide = carouselRef.current.querySelector<HTMLElement>('.carousel-slide');
      const track = carouselRef.current.querySelector<HTMLElement>('.carousel-track');

      if (!firstSlide || !track) {
        return;
      }

      const containerStyles = window.getComputedStyle(carouselRef.current);
      const paddingLeft = Number.parseFloat(containerStyles.paddingLeft || '0') || 0;
      const paddingRight = Number.parseFloat(containerStyles.paddingRight || '0') || 0;
      const containerWidth =
        carouselRef.current.clientWidth - paddingLeft - paddingRight;
      const slideWidth = firstSlide.offsetWidth;
      const gap = Number.parseFloat(window.getComputedStyle(track).gap || '16') || 16;
      const visibleSlides = Math.max(
        1,
        Math.floor((containerWidth + gap) / (slideWidth + gap)),
      );

      setSlideMetrics({ visibleSlides, slideWidth, gap });
    };

    updateSlideMetrics();

    const resizeObserver = new ResizeObserver(() => {
      updateSlideMetrics();
    });

    if (carouselRef.current) {
      resizeObserver.observe(carouselRef.current);
    }

    window.addEventListener('resize', updateSlideMetrics);

    return () => {
      resizeObserver.disconnect();
      window.removeEventListener('resize', updateSlideMetrics);
    };
  }, [slideCount]);

  useEffect(() => {
    setCurrentIndex((prev) => Math.min(prev, maxIndex));
  }, [maxIndex]);

  // Auto-play logic
  useEffect(() => {
    if (autoPlay && canNavigate) {
      autoPlayIntervalRef.current = setInterval(() => {
        handleNext();
      }, autoPlayInterval);

      return () => {
        if (autoPlayIntervalRef.current) {
          clearInterval(autoPlayIntervalRef.current);
        }
      };
    }
  }, [autoPlay, autoPlayInterval, canNavigate]);

  // Detener auto-play cuando el usuario interactúa
  const stopAutoPlay = () => {
    if (autoPlayIntervalRef.current) {
      clearInterval(autoPlayIntervalRef.current);
    }
  };

  const handleNext = () => {
    if (!isTransitioning && canNavigate) {
      stopAutoPlay();
      setIsTransitioning(true);
      setCurrentIndex((prev) => (prev >= maxIndex ? 0 : prev + 1));
      setTimeout(() => setIsTransitioning(false), 300);
    }
  };

  const handlePrev = () => {
    if (!isTransitioning && canNavigate) {
      stopAutoPlay();
      setIsTransitioning(true);
      setCurrentIndex((prev) => (prev <= 0 ? maxIndex : prev - 1));
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
            transform: `translateX(-${currentIndex * (slideMetrics.slideWidth + slideMetrics.gap)}px)`,
          }}
        >
          {testimonials.map((testimonial) => (
            <div key={testimonial.id} className='carousel-slide'>
              <CarouselCard testimonial={testimonial} />
            </div>
          ))}
        </div>

        {/* Botones de navegación */}
        {canNavigate && (
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
      {canNavigate && (
        <div className='carousel-indicators'>
          {Array.from({ length: maxIndex + 1 }).map((_, index) => (
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
