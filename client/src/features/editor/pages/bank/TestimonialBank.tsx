import TableBank from './components/TableBank';
import useTestimonialBank from '../bank/hooks/use-testimonial-bank';
import TitleContainer from '../../../editor/components/title-container/TitleContainer';
import Paginator from '../../../../shared/components/pagination/Paginator';

const TestimonialBank = () => {
  const { data, page, setPage, asocTestimonial } = useTestimonialBank();

  return (
    <section>
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
