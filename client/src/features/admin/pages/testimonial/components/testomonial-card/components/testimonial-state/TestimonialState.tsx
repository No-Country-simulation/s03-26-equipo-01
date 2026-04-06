import type { TestimonialStateProps } from "./testimonial-state";
import './testimonial-state.css';

const TestimonialState = ({testimonial}: TestimonialStateProps) => {

    const classOptions = {
        APROBADO: 'testimonial-admin-approved',
        BORRADOR: 'testimonial-admin-draft',
        PUBLICADO: 'testimonial-admin-publish',
        ARCHIVADO: 'testimonial-admin-archived'
    }

    return (
        <section className = {classOptions[testimonial.state]}>
            <div></div>
            <p>{testimonial.state}</p>
        </section>
    )
}

export default TestimonialState;