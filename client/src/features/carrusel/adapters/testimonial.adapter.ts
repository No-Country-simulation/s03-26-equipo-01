import type { CarouselTestimonial } from '../models/carouselTestimonial';

/**
 * Estructura que retorna la API para testimonios
 * Campos principales: id, testimonial, rating, witness (nombre), state, category, tags, media
 */
interface ApiTestimonial {
  id: number;
  witness: string; // Nombre del autor (no fullName)
  testimonial: string;
  rating: number;
  state?: string;
  email?: string;
  createdAt?: string;
  idEmbed?: number;
  category?: {
    id: number;
    name: string;
  };
  tags?: Array<{
    id: number;
    name: string;
  }>;
  media?: {
    imageUrl?: string;
    imagePublicId?: string;
    videoUrl?: string;
    videoId?: string;
    thumbnailUrl?: string;
  };
}

export const adaptTestimonialToCarousel = (
  testimonial: ApiTestimonial,
): CarouselTestimonial => {
  return {
    id: testimonial.id,
    fullName: testimonial.witness, // Mapear 'witness' a 'fullName'
    testimonial: testimonial.testimonial,
    rating: testimonial.rating,
    tags: testimonial.tags || [],
    youtubeUrl: testimonial.media?.videoUrl,
    image: testimonial.media?.thumbnailUrl || testimonial.media?.imageUrl,
    idEmbed: testimonial.idEmbed || parseInt(testimonial.media?.videoId || '0'),
  };
};

export const adaptTestimonials = (
  data: any,
): CarouselTestimonial[] => {
  try {
    // Manejar diferentes estructuras de respuesta
    let testimonials: ApiTestimonial[] = [];

    // Si es un array directo
    if (Array.isArray(data)) {
      testimonials = data;
    }
    // Si es un objeto con propiedad 'data' que contiene el array
    else if (data && typeof data === 'object' && Array.isArray(data.data)) {
      testimonials = data.data;
    }
    // Si es un objeto con propiedad 'testimonials' que contiene el array
    else if (data && typeof data === 'object' && Array.isArray(data.testimonials)) {
      testimonials = data.testimonials;
    }
    // Si es un objeto con propiedad 'content' que contiene el array
    else if (data && typeof data === 'object' && Array.isArray(data.content)) {
      testimonials = data.content;
    }

    // Validar que tenemos un array antes de mapear
    if (!Array.isArray(testimonials)) {
      console.warn('No se encontró array de testimonios en la respuesta:', data);
      return [];
    }

    return testimonials.map(adaptTestimonialToCarousel);
  } catch (error) {
    console.error('Error adaptando testimoniales:', error, 'Data:', data);
    return [];
  }
};
