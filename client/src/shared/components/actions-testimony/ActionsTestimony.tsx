import { Button } from '@mui/material';
import { Edit, Send } from 'lucide-react';
import './actions-testimony.css';

interface ActionsTestimonyProps {
  id: number;
  state: string;
  onEdit: (id: number) => void;
  onAdvance: (id: number) => void;
  isLoading?: boolean;
}

const ActionsTestimony = ({
  id,
  state,
  onEdit,
  onAdvance,
  isLoading = false,
}: ActionsTestimonyProps) => {
  const isDraft = state.toLowerCase().includes('draft');
  const isPending = state.toLowerCase().includes('pending');
  const canAdvance = isDraft;

  return (
    <div className='actions-testimony-container'>
      <Button
        variant='outlined'
        size='small'
        startIcon={<Edit size={16} />}
        onClick={() => onEdit(id)}
        disabled={isLoading || isPending}
        sx={{ mr: 1 }}
      >
        EDITAR
      </Button>

      {canAdvance && (
        <Button
          variant='contained'
          size='small'
          startIcon={<Send size={16} />}
          onClick={() => onAdvance(id)}
          disabled={isLoading}
          sx={{
            backgroundColor: '#f57c00',
            '&:hover': { backgroundColor: '#e65100' },
          }}
        >
          ENVIAR A REVISIÓN
        </Button>
      )}
    </div>
  );
};

export default ActionsTestimony;
