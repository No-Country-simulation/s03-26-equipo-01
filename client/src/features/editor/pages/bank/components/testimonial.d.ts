import type { Media } from './media';
import type { Tag } from './tag';

export interface TestimonialSimpleDTO {
  id: number;
  testimonial: string;
  rating: number;
  tags: Tag[];
  media: Media;
}
