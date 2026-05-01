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
import RejectModal from './components/reject-modal/RejectModal';

const TestimonialCard = ({ testimonial }: TestimonialCardProps) => {
  
  const { updateTestimonial, advance, deleted } = useTestimonialState(testimonial);
  const { id, isDiscart, isState, changeToDiscart, changeToPublished, changeToAproved, changeToDraft, refresh } = useChangeState();

  return (updateTestimonial && (
    <>
      <CardContent testimonial={updateTestimonial} changeToAproved={changeToAproved} changeToDiscart={changeToDiscart} changeToDraft={changeToDraft} changeToPublished={changeToPublished}/>
      {isState('Aprobado') && id && <AprobedModal onChangeState = {() => advance(id)} onClose = {refresh} /> }
      {isState('Publicado') && id && <PublishedModal onChangeState = {() => advance(id)} onClose = {refresh} /> }
      {isState('Borrador') && id && <RejectModal onChangeState = {() => advance(id)} onClose = {refresh} /> }
      {isDiscart && id && <DeleteModal onDelete = {() => deleted(id)} onClose = {refresh} /> }
    </>
    )
  );
};

const CardContent = ({testimonial, changeToDiscart, changeToPublished, changeToAproved, changeToDraft}: TestimonialCardContentProps) => {

  return (
    <article className='testimonial-admin-card-container falling-container'>
      <TestimonialHeader testimonial={testimonial} />
      <TestimonialState testimonial={testimonial} />
      <TestimonialDescription testimonial={testimonial} />
      <TestimonialTags tags={testimonial.tags} />
      {testimonial.media && <MultimediaContent testimonial={testimonial} />}
      <StateButtonContainer
        changeStateButtons={buttonsStateData({changeToDiscart, changeToPublished, changeToAproved, changeToDraft })[testimonial.state]}
        testimonial={testimonial}
      />
    </article>
  )
}


export default TestimonialCard;
