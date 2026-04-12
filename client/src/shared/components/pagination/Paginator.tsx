import { Pagination } from '@mui/material';
import type PaginatorProps from './pagenator';
import './styles/paginator.css';

const paginatorStyles = {
  '& .MuiPaginationItem-root': {
    color: 'var(--secondary-color)',
    fontFamily: 'var(--primary-font)',
    borderRadius: '50%',
    width: '40px',
    height: '40px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    border: '1px solid #c3c5c3',
  },
  '& .MuiPaginationItem-root.Mui-selected': {
    backgroundColor: 'var(--primary-blue)',
    color: '#fff',
    '&:hover': {
      backgroundColor: 'var(--accent-color)',
    },
  },
  '& .MuiPaginationItem-root:hover': {
    backgroundColor: '#f0f0f0',
  },
};

const Paginator = ({
  totalPages,
  currentPage,
  onPageChange,
  totalElements,
  pageSize,
}: PaginatorProps) => {
  return (
    <div className='paginator-container'>
      <span className='paginator-total'>
        {pageSize} resultados de {totalElements}
      </span>
      <Pagination
        count={totalPages}
        page={currentPage + 1}
        onChange={(_, page) => onPageChange(page - 1)}
        shape='circular'
        sx={paginatorStyles}
      />
    </div>
  );
};

export default Paginator;
