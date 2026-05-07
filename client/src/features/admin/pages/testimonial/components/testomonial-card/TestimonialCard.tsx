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
import useSelectState from './hooks/use-modal-state';
import AprobedModal from './components/aprobed-modal/AprobedModal';
import DeleteModal from '../../../../components/delete-modal/DeleteModal';
import PublishedModal from './components/published-modal/PublishedModal';
import RejectModal from './components/reject-modal/RejectModal';
import type { ChangeStateResult } from '../testimonials-container/types/change-state-result';

const TestimonialCard = ({ testimonial, onChangeState }: TestimonialCardProps) => {
  
  const { updateTestimonial, advance, deleted } = useTestimonialState(testimonial);
  const { id, isState, selectTo, refresh } = useSelectState();

  const handleChange = async (execute: () => Promise<ChangeStateResult>) => onChangeState(await execute());

  return (updateTestimonial && (
    <>
      <CardContent testimonial = {updateTestimonial} selectTo = {selectTo} />
      {isState('Aprobado') && id && <AprobedModal onChangeState = {() => handleChange(() => advance(id))} onClose = {refresh} /> }
      {isState('Publicado') && id && <PublishedModal onChangeState = {() => handleChange(() => advance(id))} onClose = {refresh} /> }
      {isState('Borrador') && id && <RejectModal onChangeState = {() => handleChange(() => advance(id))} onClose = {refresh} /> }
      {isState('Eliminado') && id && <DeleteModal onDelete = {() => handleChange(() => deleted(id))} onClose = {refresh} /> }
    </>
    )
  );
};

const CardContent = ({testimonial, selectTo}: TestimonialCardContentProps) => {

  return (
    <article className='testimonial-admin-card-container falling-container'>
      <TestimonialHeader testimonial={testimonial} />
      <TestimonialState testimonial={testimonial} />
      <TestimonialDescription testimonial={testimonial} />
      <TestimonialTags tags={testimonial.tags} />
      {testimonial.media && <MultimediaContent testimonial={testimonial} />}
      <StateButtonContainer
        changeStateButtons={buttonsStateData(selectTo)[testimonial.state]}
        testimonial={testimonial}
      />
    </article>
  )
}


export default TestimonialCard;
