// models/testimonial-response.ts
import type { Tag } from '../../../admin/models/tag';
import type { Category } from '../../../admin/models/category';

export interface MediaResponseDTO {
  videoUrl?: string;
  imageUrl?: string;
  thumbnailUrl?: string;
}

export interface TestimonialResponseDTO {
  id: number;
  testimonial: string;
  witness: string;
  rating: number;
  media: MediaResponseDTO;
  email: string;
  state: string;
  createdAt: string;
  category: Category | null;
  tags: Tag[];
}
