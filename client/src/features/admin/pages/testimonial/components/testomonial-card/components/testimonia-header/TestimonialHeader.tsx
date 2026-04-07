import type { TestimonialHeaderProps } from "./Testimonial-card";
import './testimonial.header.css';

const TestimonialHeader = ({testimonial}: TestimonialHeaderProps) => {
    return (
        <header className = 'testimonial-admin-header'>
            <section className = 'testimonial-admin-header-editor'>
                <div><p>{testimonial.witness[0].toUpperCase()}</p></div>
                <p className = 'testimonial-admin-header_editor-name'>{testimonial.witness}</p>
            </section>
            <section className = 'testimonial-admin-header-category'>
                <p>{testimonial.category.name}</p>
            </section>
        </header>
    )
}

export default TestimonialHeader;