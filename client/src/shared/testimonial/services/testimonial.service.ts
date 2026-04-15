import api from '../../../core/api/api';
import { CREATE_TESTIMONIAL_API } from '../../../core/api/urls/urls';
import type { Testimonial } from '../models/testimonial';
import getEmbedApiKey from './embed-api-key.service';

async function createTestimonial(data: Testimonial) {
  if (data.rating === null) throw new Error('La valoración debe ser un entero');
  const testimonialApiKey = getEmbedApiKey();
  if (!testimonialApiKey)
    throw new Error('Falta configurar la API key del embed');

  try {
    const formData = new FormData();
    formData.append('witness', data.fullName);
    formData.append('email', data.email);
    formData.append('testimonial', data.testimonial);
    formData.append('rating', data.rating.toString());
    data.tagIds.forEach((tagId) => formData.append('idTags', tagId.toString()));
    if (data.youtubeUrl) formData.append('youtubeUrl', data.youtubeUrl);
    if (data.image) formData.append('image', data.image);

    const response = await api.post(CREATE_TESTIMONIAL_API, formData, {
      headers: {
        'X-Api-Key': testimonialApiKey,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error en la creación de testimonio:', error);
    throw error;
  }
}

export default createTestimonial;
