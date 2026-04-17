import type { Tag } from '@/shared/types';

export interface CarouselTestimonial {
  id: number;
  fullName: string;
  testimonial: string;
  rating: number;
  tags: Tag[];
  youtubeUrl?: string;
  youtubeThumbnail?: string;
  image?: string;
  videoId?: string;
}
