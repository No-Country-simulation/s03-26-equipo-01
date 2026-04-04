import TitleContainer from './components/title-container/TitleContainer';
import './styles/testimonial-panel.css';

const TestimonialPanel = () => {
    return (
        <section className = 'testimonial-admin-panel'>
            <div className = 'testimonial-admin-panel_container'>
                <TitleContainer />
            </div>
        </section>
    )
}

export default TestimonialPanel;