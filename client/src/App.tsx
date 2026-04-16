import { BrowserRouter, Route } from 'react-router-dom';
import { Routes } from 'react-router-dom';
import {
  ADMIN_PATH,
  EDITOR_PATH,
  LOGIN_PATH,
  TESTIMONIAL_CARRUSEL,
  TESTIMONIAL_PATH,
} from './core/routes/routes';
import Login from './features/login/pages/Login';
import AuthProvider from './shared/auth/context/provide-auth';
import NotFoundPage from './shared/pages/404-not-found/NotFoundPage';
import { NewTestimonial } from './features/newTestimonialForm/pages/NewTestimonial';
import AuthValidator from './shared/auth/components/auth-validator/AuthValidator';
import Admin from './features/admin/pages/home/Admin';
import Editor from './features/editor/pages/home/Editor';
import { CarouselPage } from './features/carrusel';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path={LOGIN_PATH}
          element={
            <AuthProvider>
              <Login />
            </AuthProvider>
          }
        ></Route>
        <Route path={TESTIMONIAL_PATH} element={<NewTestimonial />} />
        <Route path={TESTIMONIAL_CARRUSEL} element={<CarouselPage />} />
        <Route
          element={
            <AuthProvider>
              <AuthValidator />
            </AuthProvider>
          }
        >
          <Route path={ADMIN_PATH} element={<Admin />} />
          <Route path={EDITOR_PATH} element={<Editor />} />
        </Route>
        <Route path={'*'} element={<NotFoundPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
