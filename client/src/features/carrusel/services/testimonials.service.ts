import api from '../../../core/api/api';
import { TESTIMONIAL_CARRUSEL } from '../../../core/routes/routes';
//import getEmbedApiKey from '../../../shared/testimonial/services/embed-api-key.service';

const testimonialApiKey = 'vza_871123560a974a859834a4e8459239d2';

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
