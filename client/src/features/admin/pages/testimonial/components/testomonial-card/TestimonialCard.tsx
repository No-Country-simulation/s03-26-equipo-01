import MultimediaContent from "./components/multimedia-content/MultimediaContent";
import TestimonialHeader from "./components/testimonia-header/TestimonialHeader";
import TestimonialDescription from "./components/testimonial-description/TestimonialDescription";
import TestimonialState from "./components/testimonial-state/TestimonialState";
import TestimonialTags from "./components/testimonial-tags/TestimonialTags";
import type { TestimonialCardProps } from "./testimonial-card";
import './styles/testomonial-card.css';
import StateButtonContainer from "../state-buttons-container/StateButtonContainer";
import buttonsStateData from "./buttons-data";
import useTestimonialState from "../../hooks/use-testimonial-state";

const TestimonialCard = ({testimonial}: TestimonialCardProps) => {

    const {updateTestimonial, nextState, prevState} = useTestimonialState(testimonial);

    return (
        <article className = 'testimonial-admin-card-container'>
            <TestimonialHeader testimonial = {updateTestimonial} />
            <TestimonialState testimonial = {updateTestimonial} />
            <TestimonialDescription testimonial = {updateTestimonial} />
            <TestimonialTags testimonial = {updateTestimonial} />
            <MultimediaContent testimonial = {updateTestimonial} />
            <StateButtonContainer 
                changeStateButtons = {buttonsStateData[updateTestimonial.state]} 
                testimonial = {updateTestimonial} 
                nextState = {nextState}
                prevState = {prevState}
            />
        </article>
    )
}

export default TestimonialCard;