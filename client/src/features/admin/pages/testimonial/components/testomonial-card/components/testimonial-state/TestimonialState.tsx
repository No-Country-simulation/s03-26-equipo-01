import type { TestimonialStateProps } from "./testimonial-state";


const TestimonialState = ({testimonial}: TestimonialStateProps) => {
    return (
        <div>
            {testimonial.state}
        </div>
    )
}

export default TestimonialState;