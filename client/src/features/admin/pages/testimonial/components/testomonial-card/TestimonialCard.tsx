import MultimediaContent from './components/multimedia-content/MultimediaContent';
import TestimonialHeader from './components/testimonia-header/TestimonialHeader';
import TestimonialDescription from './components/testimonial-description/TestimonialDescription';
import TestimonialState from './components/testimonial-state/TestimonialState';
import TestimonialTags from './components/testimonial-tags/TestimonialTags';
import type {
  TestimonialCardContentProps,
  TestimonialCardProps,
} from './types/testimonial-card';
import './styles/testomonial-card.css';
import StateButtonContainer from '../state-buttons-container/StateButtonContainer';
import buttonsStateData from './types/buttons-data';
import useTestimonialState from '../../hooks/use-testimonial-state';
import useChangeState from './hooks/use-modal-state';
import AprobedModal from './components/aprobed-modal/AprobedModal';
import DeleteModal from '../../../../components/delete-modal/DeleteModal';
import PublishedModal from './components/published-modal/PublishedModal';

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

  const { id, isDiscart, isState, changeToDiscart, changeToPublished, changeToAproved, changeToDraft  } = useChangeState();

  return (
    <>
      <article className='testimonial-admin-card-container'>
      <TestimonialHeader testimonial={testimonial} />
      <TestimonialState testimonial={testimonial} />
      <TestimonialDescription testimonial={testimonial} />
      <TestimonialTags tags={testimonial.tags} />
      <MultimediaContent testimonial={testimonial} />
      <StateButtonContainer
        changeStateButtons={
          buttonsStateData({changeToDiscart, changeToPublished, changeToAproved, changeToDraft })[testimonial.state]
        }
        testimonial={testimonial}
        />
      </article>
      {isState('Aprobado') && id && <AprobedModal onAcept = {advance} id = {id} /> }
      {isState('Publicado') && id && <PublishedModal onAcept = {advance} id = {id} /> }
      {isState('Borrador') && id && <RejectModal onAcept = {advance} id = {id} /> }
      {isDiscart && id && <DeleteModal onDelete = {deleted} id = {id} /> }
    </>
  );
};

export default TestimonialCard;
