import TitleContainer from '../../components/title-container/TitleContainer';
import EditContainer from './components/editContainer/EditContainer';
import './components/editContainer/styles/edit-container.css';

const EditTestimonial = () => {
  return (
    <main className='edit-page-container'>
      <TitleContainer title='Editar y moderar testimonio' />
      <EditContainer />
    </main>
  );
};

export default EditTestimonial;
