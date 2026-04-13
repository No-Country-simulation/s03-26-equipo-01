import api from '../../../../core/api/api';
import { EDITOR_TESTIMONIAL } from '../../../../core/api/urls/urls';
import type { TestimonialResponseDTO } from '../../models/testimonial-response';

export async function getTestimonialService(
  id: number,
): Promise<TestimonialResponseDTO> {
  const response = await api.get<TestimonialResponseDTO>(
    EDITOR_TESTIMONIAL(id),
  );
  return response.data;
}
