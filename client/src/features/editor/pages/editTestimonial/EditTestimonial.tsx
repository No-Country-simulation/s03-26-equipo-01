import { useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';
import { ArrowLeft } from 'lucide-react';
import TitleContainer from '../../components/title-container/TitleContainer';
import EditContainer from './components/editContainer/EditContainer';
import './components/editContainer/styles/edit-container.css';

const EditTestimonial = () => {
  const navigate = useNavigate();

  return (
    <main className='edit-page-container'>
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}
      >
        <TitleContainer title='Editar y moderar testimonio' />
        <Button
          variant='outlined'
          startIcon={<ArrowLeft size={16} />}
          onClick={() => navigate(-1)}
          sx={{
            borderColor: '#1a237e',
            color: '#1a237e',
            whiteSpace: 'nowrap',
          }}
        >
          VOLVER A MIS TESTIMONIOS
        </Button>
      </Box>
      <EditContainer />
    </main>
  );
};

export default EditTestimonial;
