import MultimediaContent from './components/multimedia-content/MultimediaContent';
import TestimonialHeader from './components/testimonia-header/TestimonialHeader';
import TestimonialDescription from './components/testimonial-description/TestimonialDescription';
import TestimonialState from './components/testimonial-state/TestimonialState';
import TestimonialTags from './components/testimonial-tags/TestimonialTags';
import type {
  TestimonialCardContentProps,
  TestimonialCardProps,
} from './testimonial-card';
import './styles/testomonial-card.css';
import StateButtonContainer from '../state-buttons-container/StateButtonContainer';
import buttonsStateData from './buttons-data';
import useTestimonialState from '../../hooks/use-testimonial-state';

const TestimonialCard = ({ testimonial }: TestimonialCardProps) => {
  const { updateTestimonial, advance, deleted } =
    useTestimonialState(testimonial);

  return (
    updateTestimonial && (
      <TestimonialCardContent
        testimonial={updateTestimonial}
        advance={advance}
        deleted={deleted}
      />
    )
  );
};

const TestimonialCardContent = ({
  testimonial,
  advance,
  deleted,
}: TestimonialCardContentProps) => {
  return (
    <article className='testimonial-admin-card-container'>
      <TestimonialHeader testimonial={testimonial} />
      <TestimonialState testimonial={testimonial} />
      <TestimonialDescription testimonial={testimonial} />
      <TestimonialTags tags={testimonial.tags} />
      <MultimediaContent testimonial={testimonial} />
      <StateButtonContainer
        changeStateButtons={
          buttonsStateData(advance, deleted)[testimonial.state]
        }
        testimonial={testimonial}
      />
    </article>
  );
};

export default TestimonialCard;
