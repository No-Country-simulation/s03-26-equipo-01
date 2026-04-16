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
