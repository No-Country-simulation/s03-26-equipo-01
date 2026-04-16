import api from '../../../../core/api/api';
import type { TablePaginator } from '../../../../shared/types/table/table';
import type { TestimonialEditorDTO } from '../../models/testimonial-editor';
import { MY_TESTIMONIALS_URL } from '../../../../core/api/urls/urls';
const ADVANCE_TESTIMONIAL_URL = (id: number) =>
  `/editor/testimonials/${id}/advance`;

async function getMyTestimonials(
  page: number = 0,
  size: number = 5,
): Promise<TablePaginator<TestimonialEditorDTO>> {
  const response = await api.get<TablePaginator<TestimonialEditorDTO>>(
    MY_TESTIMONIALS_URL,
    { params: { page, size } },
  );
  return response.data;
}

export async function advanceTestimonialService(
  idTestimonial: number,
): Promise<void> {
  await api.patch(ADVANCE_TESTIMONIAL_URL(idTestimonial));
}

export default getMyTestimonials;
