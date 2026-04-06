import type { TestimonialDescriptionProps } from "./testimonial-description";
import './testimonial-description.css';

const TestimonialDescription = ({testimonial}: TestimonialDescriptionProps) => {
    return (
        <section className = 'testimonial-admin-description'>
            <p>{testimonial.rating}</p>
            <p>{testimonial.testimonial}</p>
        </section>
    )
}

export default TestimonialDescription;