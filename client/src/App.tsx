import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"
import { LOGIN_PATH } from "./core/routes/routes"
import Login from "./features/login/pages/Login"
import AuthProvider from "./shared/auth/context/provide-auth"

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path = {LOGIN_PATH} element = {<Login />}></Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App
