import { useEffect, useState, useCallback } from 'react';
import type { TestimonialResponseDTO } from '../../../models/testimonial-response';
import type { Category } from '../../../../admin/models/category';
import { getTestimonialService } from '../../../services/testimonial/get-testimonial.service';

interface UseLoadTestimonialReturn {
  testimonial: TestimonialResponseDTO | null;
  loading: boolean;
  category: Category | null;
  testimonialText: string;
  showVideo: boolean;
  showImage: boolean;
  setCategory: (category: Category | null) => void;
  setTestimonialText: (text: string) => void;
  setShowVideo: (show: boolean) => void;
  setShowImage: (show: boolean) => void;
}

export const useLoadTestimonial = (
  id: string | undefined,
  initTags: (tags: any[]) => void,
): UseLoadTestimonialReturn => {
  const [testimonial, setTestimonial] = useState<TestimonialResponseDTO | null>(
    null,
  );
  const [loading, setLoading] = useState(true);
  const [category, setCategory] = useState<Category | null>(null);
  const [testimonialText, setTestimonialText] = useState('');
  const [showVideo, setShowVideo] = useState(false);
  const [showImage, setShowImage] = useState(false);

  useEffect(() => {
    if (!id) return;

    getTestimonialService(Number(id))
      .then((data) => {
        setTestimonial(data);
        initTags(data.tags);
        setCategory(data.category || null);
        setTestimonialText(data.testimonial || '');
        setShowVideo(false);
        setShowImage(false);
      })
      .finally(() => setLoading(false));
  }, [id, initTags]);

  return {
    testimonial,
    loading,
    category,
    testimonialText,
    showVideo,
    showImage,
    setCategory,
    setTestimonialText,
    setShowVideo,
    setShowImage,
  };
};
