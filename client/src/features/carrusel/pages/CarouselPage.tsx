import CarouselContainer from '../components/carousel-container/CarouselContainer';
import { useCarouselTestimonials } from '../hooks/useCarouselTestimonials';
import './carousel-page.css';

const CarouselPage = () => {
  const { testimonials, isLoading, error } = useCarouselTestimonials();

  if (error) {
    return (
      <section className='carousel-page carousel-page-error'>
        <p>Error al cargar los testimonios: {error.message}</p>
      </section>
    );
  }

  return (
    <section className='carousel-page'>
      <div className='carousel-page-container'>
        <h2 className='carousel-page-title'>Lo que dicen de nosotros</h2>
        <p className='carousel-page-subtitle'>
          Historias de éxito de nuestros estudiantes
        </p>

        {isLoading ? (
          <div className='carousel-loading'>
            <p>Cargando testimonios...</p>
          </div>
        ) : (
          <CarouselContainer testimonials={testimonials} autoPlay={true} />
        )}
      </div>
    </section>
  );
};

export default CarouselPage;
