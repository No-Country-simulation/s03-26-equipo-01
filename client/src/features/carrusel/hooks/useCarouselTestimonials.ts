import { useState, useEffect } from 'react';
import type { CarouselTestimonial } from '../models/carouselTestimonial';
import { getPublishedTestimonials } from '../services/testimonials.service';
import { adaptTestimonials } from '../adapters/testimonial.adapter';
import getEmbedApiKey from '../../../shared/testimonial/services/embed-api-key.service';

interface UseCarouselTestimonialsResult {
  testimonials: CarouselTestimonial[];
  isLoading: boolean;
  error: Error | null;
}

export const useCarouselTestimonials = (): UseCarouselTestimonialsResult => {
  const [testimonials, setTestimonials] = useState<CarouselTestimonial[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    const fetchTestimonials = async () => {
      try {
        setIsLoading(true);
        const apiKey = getEmbedApiKey();
        const data = await getPublishedTestimonials(apiKey || undefined);

        const adaptedData = adaptTestimonials(data);
        setTestimonials(adaptedData);
        setError(null);
      } catch (err) {
        const errorMessage =
          err instanceof Error ? err.message : 'Error desconocido';
        console.error('Error en useCarouselTestimonials:', err);
        setError(new Error(`Error al cargar testimonios: ${errorMessage}`));
        setTestimonials([]);
      } finally {
        setIsLoading(false);
      }
    };

    fetchTestimonials();
  }, []);

  return { testimonials, isLoading, error };
};
