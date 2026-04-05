import type { Testimonial } from "../../../../models/testimonial";


interface TestimonialsContainerProps {
    testimonials: Testimonial[]
}

const TestimonialsContainer = ({testimonials}: TestimonialsContainerProps) => {
    return (
        <section>
            {testimonials.map(testimonial => 
                <p key = {testimonial.id}>{testimonial.testimonial}</p>
            )}
        </section>
    )
}

export default TestimonialsContainer;