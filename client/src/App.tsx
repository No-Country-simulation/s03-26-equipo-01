import { BrowserRouter, Route } from "react-router-dom"
import { Routes } from "react-router-dom"
import { NewTestimonialForm } from "./features/newTestimonialForm/NewTestimonialForm"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<NewTestimonialForm/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
