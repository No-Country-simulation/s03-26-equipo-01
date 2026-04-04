import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"
import { ADMIN_PATH, EDITOR_PATH, LOGIN_PATH, TESTIMONIAL_PATH } from "./core/routes/routes"
import Login from "./features/login/pages/Login"
import AuthProvider from "./shared/auth/context/provide-auth"
import NotFoundPage from "./shared/pages/404-not-found/NotFoundPage"
import { NewTestimonial } from "./features/newTestimonialForm/pages/NewTestimonial"
import Admin from "./features/admin/pages/Admin"
import AuthValidator from "./shared/auth/components/auth-validator/AuthValidator"

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path = {LOGIN_PATH} element = {<Login />}></Route>
          <Route element = {<AuthValidator />}>
            <Route path = {ADMIN_PATH} element = {<Admin />} />
            <Route path = {EDITOR_PATH} element = {<p>Editor</p>} />
            <Route path = {TESTIMONIAL_PATH} element = {<NewTestimonial/>} />
          </Route>
          <Route path = {'*'} element = {<NotFoundPage />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  )
}

export default App
