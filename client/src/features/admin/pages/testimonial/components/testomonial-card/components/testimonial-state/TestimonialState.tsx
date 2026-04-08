import type { TestimonialStateProps } from "./testimonial-state";
import './testimonial-state.css';

const TestimonialState = ({testimonial}: TestimonialStateProps) => {

    const classOptions = {
        Aprobado: 'testimonial-admin-approved',
        Publicado: 'testimonial-admin-publish',
        Pendiente: 'testimonial-admin-pending'
    }

    return (
        <section className = {classOptions[testimonial.state]}>
            <div></div>
            <p>{testimonial.state}</p>
        </section>
    )
}

export default TestimonialState;