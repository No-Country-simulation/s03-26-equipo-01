import type { TestimonialDescriptionProps } from "./testimonial-description";


const TestimonialDescription = ({testimonial}: TestimonialDescriptionProps) => {
    return (
        <div>
            <p>{testimonial.rating}</p>
            <p>{testimonial.testimonial}</p>
        </div>
    )
}

export default TestimonialDescription;