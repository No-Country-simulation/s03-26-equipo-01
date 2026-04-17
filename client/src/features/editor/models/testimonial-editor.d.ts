import type { Category } from '../admin/models/category';
import type { Media } from '../admin/models/media';

export interface TestimonialEditorDTO {
  id: number;
  testimonial: string;
  stateTestimonial: string;
  media: Media;
  category?: Category;
}
