import { Rating } from "@mui/material";
import type { TestimonialDescriptionProps } from "./testimonial-description";
import './testimonial-description.css';

const TestimonialDescription = ({testimonial}: TestimonialDescriptionProps) => {
    return (
        <section className = 'testimonial-admin-description'>
            <Rating name = "testimonial-ranting" defaultValue = {testimonial.rating} precision = {0.5} readOnly />
            <p>{testimonial.testimonial}</p>
        </section>
    )
}

export default TestimonialDescription;