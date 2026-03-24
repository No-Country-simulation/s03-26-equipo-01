import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"
import { LOGIN_PATH } from "./core/routes/routes"
import Login from "./features/login/Login"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path = {LOGIN_PATH} element = {<Login />}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
