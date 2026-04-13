import { Route, Routes } from 'react-router-dom';

import TestimonialBank from '../pages/bank/TestimonialBank.tsx';
import MyTestimonials from '../pages/testimonial/MyTestimonial.tsx';
import {
  EDITOR_TESTIMONIAL_BANK,
  EDITOR_TESTIMONIAL,
  EDIT_TESTIMONIAL,
} from '../../../core/routes/editor/editor';
import EditTestimonial from '../pages/editTestimonial/editTestimonial.tsx';

const EditorRoutes = () => {
  return (
    <Routes>
      <Route path={EDITOR_TESTIMONIAL_BANK} element={<TestimonialBank />} />
      <Route path={EDITOR_TESTIMONIAL} element={<MyTestimonials />} />
      <Route path={EDIT_TESTIMONIAL} element={<EditTestimonial />} />
    </Routes>
  );
};

export default EditorRoutes;
