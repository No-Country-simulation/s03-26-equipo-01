import type { Row } from './table';
import type { TestimonialSimpleDTO } from './testimonial';

export default interface BodyBankActionProps {
  rows: Row<TestimonialSimpleDTO>[];
  onAsoc: (id: number) => void;
  currentPage: number;
  pageSize: number;
}
