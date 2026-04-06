import type { StateButtonContainerProps } from "./state-buttons-container";
import './state-buttons-container.css'; 

const StateButtonContainer = ({changeStateButtons, testimonial}: StateButtonContainerProps) => {
    return (
        <section className = "testimonial-admin-state-buttons-container">
            <button 
                className = 'testimonial-admin-state_prev-state-button'
                onClick = {() => changeStateButtons.prevState.event(testimonial.id)}
            >
                {changeStateButtons.prevState.text}</button>
            <button 
                className = 'testimonial-admin-state_next-state-button'
                onClick = {() => changeStateButtons.nextState.event(testimonial.id)}
            >
                {changeStateButtons.nextState.text}</button>
        </section>
    )
}

export default StateButtonContainer;