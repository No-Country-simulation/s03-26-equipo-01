import type { TestimonialHeaderProps } from "./Testimonial-card";
import './testimonial.header.css';

const TestimonialHeader = ({testimonial}: TestimonialHeaderProps) => {
    return (
        <header className = 'testimonial-admin-header'>
            {testimonial.email}
        </header>
    )
}

export default TestimonialHeader;