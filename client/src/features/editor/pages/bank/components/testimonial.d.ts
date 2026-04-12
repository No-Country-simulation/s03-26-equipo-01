import { Tag } from '../../../../../features/admin/models/tag';
import { Media } from '../../../../../features/admin/models/media';

export interface TestimonialSimpleDTO {
  id: number;
  testimonial: string;
  rating: number;
  tags: Tag[];
  media: Media;
}
