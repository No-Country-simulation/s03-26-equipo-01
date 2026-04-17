import CarouselContainer from '../carousel-container/CarouselContainer';
import type { CarouselTestimonial } from '../../models/carouselTestimonial';
import './carousel-demo.css';

// Datos de demostración
const DEMO_TESTIMONIALS: CarouselTestimonial[] = [
  {
    id: 1,
    fullName: 'María García',
    testimonial:
      'Gracias a este curso logré conseguir mi primer trabajo como desarrolladora. Los contenidos son muy claros y los mentores siempre están disponibles para resolver dudas.',
    rating: 10,
    tags: [
      { id: 1, name: 'React' },
      { id: 2, name: 'Frontend' },
    ],
    image: 'https://via.placeholder.com/400x225?text=Mar%C3%ADa',
  },
  {
    id: 2,
    fullName: 'Carlos López',
    testimonial:
      'La mejor inversión que pude haber hecho en mi carrera. El plan de estudios es muy completo y cubre todo lo necesario para ser un desarrollador full stack competitivo en el mercado.',
    rating: 9,
    tags: [
      { id: 3, name: 'Full Stack' },
      { id: 4, name: 'Backend' },
    ],
  },
  {
    id: 3,
    fullName: 'Ana Martínez',
    testimonial:
      'Increíble la calidad de enseñanza. Los proyectos prácticos me permitieron construir un portafolio sólido que me ayudó a conseguir un trabajo muy bien remunerado.',
    rating: 10,
    tags: [
      { id: 5, name: 'Portfolio' },
      { id: 1, name: 'React' },
    ],
    image: 'https://via.placeholder.com/400x225?text=Ana',
  },
  {
    id: 4,
    fullName: 'Diego Fernández',
    testimonial:
      'La comunidad es excelente, siempre hay gente disponible para ayudar. Aprendí mucho no solo de los cursos sino también de los compañeros.',
    rating: 8,
    tags: [
      { id: 6, name: 'Comunidad' },
      { id: 4, name: 'Backend' },
    ],
  },
  {
    id: 5,
    fullName: 'Sofía Rodríguez',
    testimonial:
      'Cambié de carrera completamente gracias a este curso. Los fundamentos están muy bien explicados y el avance es gradual y bien estructurado.',
    rating: 9,
    tags: [
      { id: 7, name: 'Principiante' },
      { id: 1, name: 'React' },
    ],
    image: 'https://via.placeholder.com/400x225?text=Sof%C3%ADa',
  },
];

interface CarouselDemoProps {
  autoPlay?: boolean;
  autoPlayInterval?: number;
}

const CarouselDemo = ({
  autoPlay = true,
  autoPlayInterval = 5000,
}: CarouselDemoProps) => {
  return (
    <div className='carousel-demo'>
      <div className='carousel-demo-header'>
        <h1 className='carousel-demo-title'>Demo: Carrusel de Testimonios</h1>
        <p className='carousel-demo-description'>
          Ejemplo funcional del carrusel con datos de demostración
        </p>
      </div>

      <div className='carousel-demo-container'>
        <CarouselContainer
          testimonials={DEMO_TESTIMONIALS}
          autoPlay={autoPlay}
          autoPlayInterval={autoPlayInterval}
        />
      </div>

      <div className='carousel-demo-info'>
        <h2>Información</h2>
        <ul>
          <li>Total de testimonios: {DEMO_TESTIMONIALS.length}</li>
          <li>Auto-play: {autoPlay ? 'Activado' : 'Desactivado'}</li>
          <li>Intervalo: {autoPlayInterval}ms</li>
          <li>Prueba los botones de navegación</li>
          <li>Haz clic en los puntos para saltar a un testimonio específico</li>
        </ul>
      </div>
    </div>
  );
};

export default CarouselDemo;
