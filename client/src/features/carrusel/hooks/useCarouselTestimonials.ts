import { useState, useEffect } from 'react';
import type { CarouselTestimonial } from '../models/carouselTestimonial';
import { getPublishedTestimonials } from '../services/testimonials.service';
import { adaptTestimonials } from '../adapters/testimonial.adapter';

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
        const data = await getPublishedTestimonials();
        
        // Debug: loguear qué recibimos de la API
        console.log('Datos crudos de API:', data);
        console.log('Tipo de datos:', typeof data);
        console.log('Es array:', Array.isArray(data));

        const adaptedData = adaptTestimonials(data);
        
        console.log('Datos adaptados:', adaptedData);
        console.log('Cantidad de testimonios:', adaptedData.length);

        setTestimonials(adaptedData);
        setError(null);
      } catch (err) {
        const errorMessage = err instanceof Error ? err.message : 'Error desconocido';
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
