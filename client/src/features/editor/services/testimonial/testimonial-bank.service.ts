import api from '../../../../core/api/api';
import {
  TESTIMONIAL_BANK,
  TESTIMONIAL_ASOC,
} from '../../../../core/api/urls/urls';
import type { TableResponseDTO } from '../../pages/bank/components/table';
import type { TestimonialSimpleDTO } from '../../pages/bank/components/testimonial';

async function testimonialBankService(
  page: number = 0,
  size: number = 5,
): Promise<TableResponseDTO<TestimonialSimpleDTO>> {
  const response = await api.get<TableResponseDTO<TestimonialSimpleDTO>>(
    TESTIMONIAL_BANK,
    { params: { page, size } },
  );
  return response.data;
}

export async function asocTestimonialService(
  idTestimonial: number,
): Promise<void> {
  await api.patch(TESTIMONIAL_ASOC(idTestimonial));
}

export default testimonialBankService;
