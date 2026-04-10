import type { StateButtonContainerProps } from "./state-buttons-container";
import './styles/state-buttons-container.css';

const StateButtonContainer = ({changeStateButtons, testimonial, nextState, prevState}: StateButtonContainerProps) => {
    return (
        <section className = "testimonial-admin-state-buttons-container">
            <button 
                className = 'testimonial-admin-state_prev-state-button'
                onClick = {() => prevState(testimonial.id)}
            >
                {changeStateButtons.prevButtonName}</button>
            <button 
                className = 'testimonial-admin-state_next-state-button'
                onClick = {() => nextState(testimonial.id)}
            >
                {changeStateButtons.nextButtonName}</button>
        </section>
    )
}

export default StateButtonContainer;