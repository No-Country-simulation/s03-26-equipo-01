import TestimonialCard from "../testomonial-card/TestimonialCard";
import type { TestimonialsContainerProps } from "./testomonial-container";

const TestimonialsContainer = ({testimonials}: TestimonialsContainerProps) => {
    return (
        <section>
            {testimonials.map(testimonial => 
                <TestimonialCard 
                    key = {testimonial.id}
                    testimonial = {testimonial}
                />
            )}
        </section>
    )
}

export default TestimonialsContainer;