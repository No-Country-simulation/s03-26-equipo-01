import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<p></p>}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
