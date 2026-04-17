import { Button } from '@mui/material';
import { Edit, Download } from 'lucide-react';
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
          variant='outlined'
          size='small'
          startIcon={<Download size={14} />}
          onClick={() => onAdvance(id)}
          disabled={isLoading}
          sx={{
            color: '#3f51b5',
            borderColor: '#3f51b5',
            '&:hover': {
              borderColor: '#3f51b5',
              backgroundColor: 'rgba(63, 81, 181, 0.04)',
            },
            padding: '4px 8px',
            fontSize: '0.75rem',
            minWidth: 'auto',
          }}
        >
          ENVIAR A REVISIÓN
        </Button>
      )}
    </div>
  );
};

export default ActionsTestimony;
