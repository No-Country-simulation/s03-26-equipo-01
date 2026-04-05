import type { TestimonialDescriptionProps } from "./testimonial-description";


const TestimonialDescription = ({testimonial}: TestimonialDescriptionProps) => {
    return (
        <section>
            <p>{testimonial.rating}</p>
            <p>{testimonial.testimonial}</p>
        </section>
    )
}

export default TestimonialDescription;