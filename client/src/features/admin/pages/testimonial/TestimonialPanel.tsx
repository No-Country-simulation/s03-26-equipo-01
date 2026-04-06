import useTestimonialPanel from '../../hooks/use-testimonial-panel';
import FiltersContainer from './components/filters-container/FiltersContainer';
import TestimonialsList from './components/testimonials-container/TestimonialContainer';
import TitleContainer from './components/title-container/TitleContainer';
import './styles/testimonial-panel.css';

const TestimonialPanel = () => {

    const {adminResources, testimonials, sendFilter} = useTestimonialPanel();
    
    return (
        <section className = 'testimonial-admin-panel'>
            <div className = 'testimonial-admin-panel_container'>
                <div className = 'testimonial-admin-panel_container--info'>
                    <TitleContainer />
                    {adminResources && <FiltersContainer 
                        adminResources = {adminResources} 
                        onFilter = {sendFilter} />
                    }
                </div>
                {testimonials && <TestimonialsList testimonials = {testimonials} />}
            </div>
        </section>
    )
}

export default TestimonialPanel;