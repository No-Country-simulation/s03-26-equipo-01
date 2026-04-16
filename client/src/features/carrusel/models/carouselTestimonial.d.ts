import type { Tag } from '@/shared/types';

export interface CarouselTestimonial {
  id: number;
  fullName: string;
  testimonial: string;
  rating: number;
  tags: Tag[];
  youtubeUrl?: string;
  image?: string;
  idEmbed?: number;
}
