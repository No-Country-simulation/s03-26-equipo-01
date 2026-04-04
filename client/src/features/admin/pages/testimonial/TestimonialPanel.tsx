import useTestimonialPanel from '../../hooks/use-testimonial-panel';
import FiltersContainer from './components/filters-container/FiltersContainer';
import TitleContainer from './components/title-container/TitleContainer';
import './styles/testimonial-panel.css';

const TestimonialPanel = () => {

    const {adminResources, sendFilter} = useTestimonialPanel();
    
    return (
        <section className = 'testimonial-admin-panel'>
            <div className = 'testimonial-admin-panel_container'>
                <TitleContainer />
                {adminResources && <FiltersContainer 
                    adminResources = {adminResources} 
                    onFilter = {sendFilter} />}
            </div>
        </section>
    )
}

export default TestimonialPanel;