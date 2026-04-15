import { UPDATE_TESTIMONIAL } from '../../../../../core/api/urls/urls';
import api from '../../../../../core/api/api';
import type {
  TestimonialUpdateDTO,
  TestimonialResponseDTO,
} from './update-testimonial.types';

export const updateTestimonialService = async (
  updateData: TestimonialUpdateDTO,
): Promise<TestimonialResponseDTO> => {
  try {
    const response = await api.put<TestimonialResponseDTO>(UPDATE_TESTIMONIAL, {
      id: updateData.id,
      categoryId: updateData.categoryId,
      testimonial: updateData.testimonial,
      tagIds: updateData.tags.map((t) => t.id),
      removedTagIds: updateData.removedTagIds,
      displayOptions: {
        showVideo: updateData.displayOptions.showVideo,
        showImage: updateData.displayOptions.showImage,
      },
    });

    return response.data;
  } catch (error) {
    console.error('Error al actualizar testimonio:', error);
    throw error;
  }
};
