import { Route, Routes } from 'react-router-dom';

import TestimonialBank from '../pages/bank/TestimonialBank.tsx';
import MyTestimonials from '../pages/testimonial/MyTestimonial.tsx';
import {
  EDITOR_TESTIMONIAL_BANK,
  EDITOR_TESTIMONIAL,
} from '../../../core/routes/editor/editor';

const EditorRoutes = () => {
  return (
    <Routes>
      <Route path={EDITOR_TESTIMONIAL_BANK} element={<TestimonialBank />} />
      <Route path={EDITOR_TESTIMONIAL} element={<MyTestimonials />} />
    </Routes>
  );
};

export default EditorRoutes;
