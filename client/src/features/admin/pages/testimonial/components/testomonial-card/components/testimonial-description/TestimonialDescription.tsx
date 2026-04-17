import { Rating } from "@mui/material";
import type { TestimonialDescriptionProps } from "./testimonial-description";
import './testimonial-description.css';

const TestimonialDescription = ({testimonial}: TestimonialDescriptionProps) => {
    const normalizedRating = Math.max(0, Math.min(5, testimonial.rating / 2));

    return (
        <section className = 'testimonial-admin-description'>
            <Rating
                name = "testimonial-ranting"
                value = {normalizedRating}
                precision = {0.5}
                readOnly
            />
            <p>{testimonial.testimonial}</p>
        </section>
    )
}

export default TestimonialDescription;
