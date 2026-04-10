import MultimediaContent from "./components/multimedia-content/MultimediaContent";
import TestimonialHeader from "./components/testimonia-header/TestimonialHeader";
import TestimonialDescription from "./components/testimonial-description/TestimonialDescription";
import TestimonialState from "./components/testimonial-state/TestimonialState";
import TestimonialTags from "./components/testimonial-tags/TestimonialTags";
import type { TestimonialCardProps } from "./testimonial-card";
import './styles/testomonial-card.css';
import StateButtonContainer from "../state-buttons-container/StateButtonContainer";
import buttonsStateData from "./buttons-data";

const TestimonialCard = ({testimonial}: TestimonialCardProps) => {
    
    return (
        <article className = 'testimonial-admin-card-container'>
            <TestimonialHeader testimonial = {testimonial} />
            <TestimonialState testimonial = {testimonial} />
            <TestimonialDescription testimonial = {testimonial} />
            <TestimonialTags testimonial = {testimonial} />
            <MultimediaContent testimonial = {testimonial} />
            <StateButtonContainer 
                changeStateButtons = {buttonsStateData[testimonial.state]} 
                testimonial = {testimonial} />
        </article>
    )
}

export default TestimonialCard;