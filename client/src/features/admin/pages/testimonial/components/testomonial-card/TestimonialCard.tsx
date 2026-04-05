import MultimediaContent from "./components/multimedia-content/MultimediaContent";
import TestimonialHeader from "./components/testimonia-header/TestimonialHeader";
import TestimonialDescription from "./components/testimonial-description/TestimonialDescription";
import TestimonialState from "./components/testimonial-state/TestimonialState";
import TestimonialTags from "./components/testimonial-tags/TestimonialTags";
import type { TestimonialCardProps } from "./testimonial-card";

//<DecisionsContainer testimonial = {testimonial} />
const TestimonialCard = ({testimonial}: TestimonialCardProps) => {
    return (
        <article>
            <TestimonialHeader testimonial = {testimonial} />
            <TestimonialState testimonial = {testimonial} />
            <TestimonialDescription testimonial = {testimonial} />
            <TestimonialTags testimonial = {testimonial} />
            <MultimediaContent testimonial = {testimonial} />
            
        </article>
    )
}

export default TestimonialCard;