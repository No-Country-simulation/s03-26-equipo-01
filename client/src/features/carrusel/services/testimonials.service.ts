import api from '../../../core/api/api';
import { TESTIMONIAL_CARRUSEL } from '../../../core/routes/routes';
import getEmbedApiKey from '../../../shared/testimonial/services/embed-api-key.service';

/**
 * Request public testimonials. The apiKey should come from the embed (query param, window, or import.meta).
 */
export const getPublicTestimonials = async (apiKey?: string) => {
  const keyToUse = apiKey || getEmbedApiKey();

  try {
    const response = await api.post(TESTIMONIAL_CARRUSEL, null, {
      headers: {
        'X-Api-Key': keyToUse,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching public testimonials:', error);
    throw error;
  }
};

/**
 * Request published testimonials. The apiKey should come from the embed (query param, window, or import.meta).
 */
export const getPublishedTestimonials = async (apiKey?: string) => {
  const keyToUse = apiKey || getEmbedApiKey();

  try {
    const response = await api.post(TESTIMONIAL_CARRUSEL, null, {
      headers: {
        'X-Api-Key': keyToUse,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching published testimonials:', error);
    throw error;
  }
};
