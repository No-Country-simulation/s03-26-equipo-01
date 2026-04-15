export interface DisplayOptionsDTO {
  showVideo: boolean;
  showImage: boolean;
}

export interface TestimonialUpdateDTO {
  id: number;
  categoryId: number | null;
  testimonial: string;
  tags: Array<{ id: number; name: string }>;
  removedTagIds: number[];
  displayOptions: DisplayOptionsDTO;
}

export interface TestimonialResponseDTO {
  id: number;
  witness: string;
  testimonial: string;
  rating: number;
  email: string;
  category: {
    id: number;
    name: string;
  } | null;
  tags: Array<{ id: number; name: string }>;
  state: string;
  media: {
    id: string;
    url: string | null;
    videoUrl: string | null;
    thumbnailUrl: string | null;
  } | null;
}
