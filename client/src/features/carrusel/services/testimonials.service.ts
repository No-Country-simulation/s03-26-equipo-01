import api from '../../../core/api/api';
import { TESTIMONIAL_CARRUSEL } from '../../../core/routes/routes';
//import getEmbedApiKey from '../../../shared/testimonial/services/embed-api-key.service';

const testimonialApiKey = 'vza_0a590875892e477bbad69caa32b61520';

export const getPublicTestimonials = async () => {
  try {
    const response = await api.post(TESTIMONIAL_CARRUSEL, null, {
      headers: {
        'X-Api-Key': testimonialApiKey,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching public testimonials:', error);
    throw error;
  }
};

export const getPublishedTestimonials = async () => {
  try {
    const response = await api.post(TESTIMONIAL_CARRUSEL, null, {
      headers: {
        'X-Api-Key': testimonialApiKey,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching published testimonials:', error);
    throw error;
  }
};
