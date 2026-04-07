import type { TestimonialHeaderProps } from "./Testimonial-card";
import './testimonial.header.css';

const TestimonialHeader = ({testimonial}: TestimonialHeaderProps) => {
    return (
        <header className = 'testimonial-admin-header'>
            <p>{testimonial.witness}</p>
            <p>{testimonial.category.name}</p>
        </header>
    )
}

export default TestimonialHeader;