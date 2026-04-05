import type { TestimonialHeaderProps } from "./Testimonial-card";


const TestimonialHeader = ({testimonial}: TestimonialHeaderProps) => {
    return (
        <header>
            {testimonial.email}
        </header>
    )
}

export default TestimonialHeader;