import type { DataRow } from '../../../../types/table/table';

export interface BodyTestimonyProps<
  T extends TestimonialData = TestimonialData,
> {
  rows: DataRow<T>[];
  currentPage: number;
  pageSize: number;
  columns: string[];
}

export interface TestimonialData {
  testimonial: string;
  media?: {
    videoUrl?: string;
    imageUrl?: string;
    thumbnailUrl?: string;
  };
  tags?: Array<{ id: number; name: string }>;
  rating?: number;
  category?: { id?: number; name?: string };
  stateTestimonial?: string;
  state?: string;
  actions?: React.ReactNode;
}
