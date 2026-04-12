import TableBank from './components/TableBank';
import useTestimonialBank from '../bank/hooks/use-testimonial-bank';
import TitleContainer from '../../../editor/components/title-container/TitleContainer';
import Paginator from '../../../../shared/components/pagination/Paginator';
import Toast from '../../../../shared/components/toast/Toast';

const TestimonialBank = () => {
  const { data, page, setPage, asocTestimonial, toast, closeToast } =
    useTestimonialBank();

  return (
    <section>
      {toast && (
        <Toast content={toast.content} type={toast.type} onClose={closeToast} />
      )}

      <TitleContainer
        title='Banco de testimonios'
        text='Selecciona y gestiona tus testimonios.'
      />

      {data && (
        <>
          <TableBank tableData={data} onAsoc={asocTestimonial} />
          <Paginator
            totalPages={data.totalPages}
            currentPage={page}
            onPageChange={setPage}
            totalElements={data.totalElements}
            pageSize={data.size}
          />
        </>
      )}
    </section>
  );
};

export default TestimonialBank;
