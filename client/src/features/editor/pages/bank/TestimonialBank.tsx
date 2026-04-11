import TableBank from './components/TableBank';
import useTestimonialBank from '../bank/hooks/use-testimonial-bank';
import TitleContainer from '../../../editor/components/title-container/TitleContainer';

const TestimonialBank = () => {
  const { data } = useTestimonialBank();

  const handleAsoc = (id: number) => {
    console.log('Pasar a trabajo:', id);
  };

  return (
    <section>
      <TitleContainer
        title='Banco de testimonios'
        text='Selecciona y gestiona tus testimonios.'
      />
      {data && <TableBank tableData={data} onAsoc={handleAsoc} />}
    </section>
  );
};

export default TestimonialBank;
