import "./styles/newTestimonial.css"
import NewTestimonialContainer from "../components/new-testimonial-container/NewTestimonialContainer";
import { ThemeProvider } from "@mui/material/styles";
import theme from "./styles/theme";

export const NewTestimonial = () => {
  return (
    <ThemeProvider theme={theme}>
      <section className="new-testimonial-page">
        <NewTestimonialContainer />
      </section>
    </ThemeProvider>
  )
}
