import TestimonialCard from "../testomonial-card/TestimonialCard";
import type { TestimonialsContainerProps } from "./testomonial-container";
import './styles/testimonials-container.css';

const TestimonialsList = ({testimonials}: TestimonialsContainerProps) => {
    return (
        <section className = 'testimonials-admin-list'>
            {testimonials.map(testimonial => 
                <TestimonialCard 
                    key = {testimonial.id}
                    testimonial = {testimonial}
                />
            )}
        </section>
    )
}

export default TestimonialsList;