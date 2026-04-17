import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AxiosError } from 'axios';

import TitleContainer from '../../components/title-container/TitleContainer';

import useMyTestimonials from './hooks/use-my-testimonials';
import { advanceTestimonialService } from '../../services/testimonial/testimonial-my.service';
import type { ReactNode } from 'react';
import ActionsTestimony from '../../../../shared/components/actions-testimony/ActionsTestimony';
import Toast from '../../../../shared/components/toast/Toast';
import GenericTable from '../../../../shared/components/table-container/components/generic-table/GenericTable';
import BodyTestimony from '../../../../shared/components/table-container/components/body-testimony/BodyTestimony';
import Paginator from '../../../../shared/components/pagination/Paginator';
import { editTestimonialPath } from '../../../../core/routes/routes';
import './my-testimonial.css';

interface ToastState {
  content: ReactNode;
  type: 'success' | 'error';
}

const MyTestimonials = () => {
  const navigate = useNavigate();
  const { data, page, setPage, refetch, isLoading } = useMyTestimonials();
  const [toast, setToast] = useState<ToastState | null>(null);

  const handleEdit = (id: number) => {
    navigate(editTestimonialPath(id));
  };

  const handleAdvance = async (id: number) => {
    try {
      await advanceTestimonialService(id);
      setToast({
        type: 'success',
        content: (
          <>
            <strong style={{ display: 'block', marginBottom: '4px' }}>
              ¡Éxito!
            </strong>
            <span>Testimonio enviado a revisión correctamente.</span>
          </>
        ),
      });
      await refetch();
    } catch (error) {
      const errorMessage =
        (error as AxiosError<{ message: string }>)?.response?.data?.message ||
        'Error al enviar el testimonio a revisión. Por favor intenta de nuevo.';
      setToast({
        type: 'error',
        content: errorMessage,
      });
      console.error('Error advancing testimonial:', error);
    }
  };

  const closeToast = () => setToast(null);

  // Mapear las acciones a cada fila
  const rowsWithActions =
    data?.rows.map((row) => ({
      ...row,
      actions: (
        <ActionsTestimony
          id={row.id}
          state={row.data.stateTestimonial}
          onEdit={handleEdit}
          onAdvance={handleAdvance}
          isLoading={isLoading}
        />
      ),
    })) || [];

  return (
    <section className='my-testimonial-container'>
      <div className='my-testimonial-container_info'>
        {toast && (
          <Toast
            content={toast.content}
            type={toast.type}
            onClose={closeToast}
          />
        )}

        <TitleContainer
          title='Mis testimonios'
          text='Aquí puedes editar, moderar y enviar tus testimonios a revisión.'
        />

        {data && (
          <>
            <GenericTable
              data={data}
              renderBody={
                <BodyTestimony
                  rows={rowsWithActions}
                  currentPage={data.page}
                  pageSize={data.size}
                  columns={data.headers}
                />
              }
            />
            <Paginator
              totalPages={data.totalPages}
              currentPage={page}
              onPageChange={setPage}
              totalElements={data.totalElements}
              pageSize={data.size}
            />
          </>
        )}
      </div>
    </section>
  );
};

export default MyTestimonials;
