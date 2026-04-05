import type { TestimonialStateProps } from "./testimonial-state";


const TestimonialState = ({testimonial}: TestimonialStateProps) => {
    return (
        <section>
            {testimonial.state}
        </section>
    )
}

export default TestimonialState;