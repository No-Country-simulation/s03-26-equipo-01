import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"
import { ADMIN_PATH, EDITOR_PATH, LOGIN_PATH } from "./core/routes/routes"
import Login from "./features/login/pages/Login"
import AuthProvider from "./shared/auth/context/provide-auth"

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path = {LOGIN_PATH} element = {<Login />}></Route>
          <Route path = {ADMIN_PATH} element = {<p>Admin</p>}></Route>
          <Route path = {EDITOR_PATH} element = {<p>Editor</p>}></Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  )
}

export default App
