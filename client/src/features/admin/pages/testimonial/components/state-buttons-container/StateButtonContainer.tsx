import type { StateButtonContainerProps } from "./state-buttons-container";
import './styles/state-buttons-container.css';

const StateButtonContainer = ({changeStateButtons, testimonial}: StateButtonContainerProps) => {
    return (
        <section className = "testimonial-admin-state-buttons-container">
            <button 
                className = 'testimonial-admin-state_prev-state-button'
                onClick = {() => changeStateButtons.discartTestimonial.event(testimonial.id)}
            >
                {changeStateButtons.discartTestimonial.textButton}</button>
            <button 
                className = 'testimonial-admin-state_next-state-button'
                onClick = {() => changeStateButtons.nextState.event(testimonial.id)}
            >
                {changeStateButtons.nextState.textButton}</button>
        </section>
    )
}

export default StateButtonContainer;