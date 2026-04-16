import useTableData from '../../../../../shared/hooks/use-table-data';
import type { TestimonialEditorDTO } from '../../../models/testimonial-editor';
import getMyTestimonials from '../../../services/testimonial/testimonial-my.service';

const useMyTestimonials = () => {
  return useTableData<TestimonialEditorDTO>({
    execute: getMyTestimonials,
    onError: (error) => console.error('Error fetching my testimonials:', error),
  });
};

export default useMyTestimonials;
