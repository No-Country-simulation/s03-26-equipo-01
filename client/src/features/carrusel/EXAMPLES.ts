import type { CarouselTestimonial } from './models/carouselTestimonial';

/**
 * EJEMPLOS DE USO - Carrusel de Testimonios
 * ==========================================
 *
 * Este archivo contiene ejemplos prácticos de cómo usar el carrusel
 * en diferentes escenarios.
 */

// ============================================================================
// EJEMPLO 1: Usar la página completa en rutas
// ============================================================================

/**
 * Opción más simple: usar la página completa que incluye:
 * - Título y descripción
 * - Obtención automática de datos de API
 * - Manejo de carga y errores
 * - Auto-play activado por defecto
 */
export function Example1_FullPage() {
  // En tu archivo de rutas (routes.ts)
  return `
  import { CarouselPage } from '@/features/carrusel';

  // Agregar esta ruta
  {
    path: '/testimonios',
    element: <CarouselPage />,
  }
  `;
}

// ============================================================================
// EJEMPLO 2: Usar el carrusel en una página existente
// ============================================================================

export function Example2_EmbedInPage() {
  return `
  import { CarouselContainer, useCarouselTestimonials } from '@/features/carrusel';

  export function HomePage() {
    const { testimonials, isLoading, error } = useCarouselTestimonials();

    if (error) {
      return <p>Error cargando testimonios</p>;
    }

    return (
      <div>
        <h1>Bienvenido</h1>
        <p>Contenido principal...</p>
        
        {/* Carrusel de testimonios */}
        {isLoading ? (
          <p>Cargando testimonios...</p>
        ) : testimonials.length > 0 ? (
          <CarouselContainer
            testimonials={testimonials}
            autoPlay={true}
            autoPlayInterval={5000}
          />
        ) : (
          <p>No hay testimonios disponibles</p>
        )}
      </div>
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 3: Carrusel sin auto-play (navegación manual)
// ============================================================================

export function Example3_ManualNavigation() {
  return `
  import { CarouselContainer, useCarouselTestimonials } from '@/features/carrusel';

  export function TestimonialsManual() {
    const { testimonials } = useCarouselTestimonials();

    return (
      <section>
        <h2>Lo que dicen de nosotros</h2>
        
        {/* Sin auto-play - solo navegación con botones */}
        <CarouselContainer testimonials={testimonials} autoPlay={false} />
      </section>
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 4: Carrusel con datos locales (para testing)
// ============================================================================

export function Example4_LocalData() {
  return `
  import { CarouselContainer } from '@/features/carrusel';
  import type { CarouselTestimonial } from '@/features/carrusel';

  const mockTestimonials: CarouselTestimonial[] = [
    {
      id: 1,
      fullName: 'Juan Pérez',
      testimonial: 'Excelente curso, aprendí mucho...',
      rating: 10,
      tags: [
        { id: 1, name: 'React' },
        { id: 2, name: 'Frontend' },
      ],
      image: 'https://example.com/image.jpg',
    },
    {
      id: 2,
      fullName: 'María García',
      testimonial: 'Muy buena experiencia, la recomiendo...',
      rating: 9,
      tags: [{ id: 3, name: 'Backend' }],
      youtubeUrl: 'https://youtube.com/watch?v=...',
      idEmbed: 'dQw4w9WgXcQ',
    },
  ];

  export function TestimonialsDemo() {
    return <CarouselContainer testimonials={mockTestimonials} />;
  }
  `;
}

// ============================================================================
// EJEMPLO 5: Usar solo componentes individuales
// ============================================================================

export function Example5_IndividualComponents() {
  return `
  import { CarouselCard } from '@/features/carrusel';
  import type { CarouselTestimonial } from '@/features/carrusel';

  const testimonial: CarouselTestimonial = {
    id: 1,
    fullName: 'Ana López',
    testimonial: 'Testimonio excelente...',
    rating: 10,
    tags: [{ id: 1, name: 'React' }],
    image: 'https://example.com/image.jpg',
  };

  // Mostrar solo una tarjeta
  export function SingleTestimonialCard() {
    return <CarouselCard testimonial={testimonial} />;
  }

  // Grid de tarjetas individuales
  export function TestimonialGrid() {
    const testimonials = [/* ... */];

    return (
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '1rem' }}>
        {testimonials.map((t) => (
          <CarouselCard key={t.id} testimonial={t} />
        ))}
      </div>
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 6: Carrusel con intervalo personalizado
// ============================================================================

export function Example6_CustomInterval() {
  return `
  import { CarouselContainer, useCarouselTestimonials } from '@/features/carrusel';

  export function FastCarousel() {
    const { testimonials } = useCarouselTestimonials();

    return (
      <CarouselContainer
        testimonials={testimonials}
        autoPlay={true}
        autoPlayInterval={3000}  // Cambiar cada 3 segundos
      />
    );
  }

  export function SlowCarousel() {
    const { testimonials } = useCarouselTestimonials();

    return (
      <CarouselContainer
        testimonials={testimonials}
        autoPlay={true}
        autoPlayInterval={8000}  // Cambiar cada 8 segundos
      />
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 7: Usar el Demo component para testing
// ============================================================================

export function Example7_DemoComponent() {
  return `
  import { CarouselDemo } from '@/features/carrusel';

  // Componente demo con datos de ejemplo incluidos
  export function TestingPage() {
    return (
      <div>
        <h1>Testing Carrusel</h1>
        
        {/* Demo con auto-play activado */}
        <CarouselDemo autoPlay={true} autoPlayInterval={5000} />
      </div>
    );
  }

  // Sin auto-play
  export function TestingPageManual() {
    return <CarouselDemo autoPlay={false} />;
  }
  `;
}

// ============================================================================
// EJEMPLO 8: Carrusel reactivo (actualizar testimonios)
// ============================================================================

export function Example8_ReactiveCarousel() {
  return `
  import { useState, useEffect } from 'react';
  import { CarouselContainer, getPublishedTestimonials, adaptTestimonials } from '@/features/carrusel';
  import type { CarouselTestimonial } from '@/features/carrusel';

  export function ReactiveCarousel() {
    const [testimonials, setTestimonials] = useState<CarouselTestimonial[]>([]);
    const [refresh, setRefresh] = useState(0);

    useEffect(() => {
      getPublishedTestimonials()
        .then((data) => setTestimonials(adaptTestimonials(data)))
        .catch((err) => console.error(err));
    }, [refresh]);

    return (
      <div>
        <button onClick={() => setRefresh((prev) => prev + 1)}>
          Actualizar testimonios
        </button>

        <CarouselContainer testimonials={testimonials} autoPlay={false} />
      </div>
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 9: Integrar en layout principal
// ============================================================================

export function Example9_MainLayout() {
  return `
  import { CarouselPage } from '@/features/carrusel';

  export function MainLayout() {
    return (
      <div>
        {/* Header */}
        <header>
          <nav>{/* Navegación */}</nav>
        </header>

        {/* Main Content */}
        <main>
          <section className="hero">
            {/* Sección hero */}
          </section>

          {/* Carrusel de testimonios */}
          <CarouselPage />

          <section className="cta">
            {/* Call to action */}
          </section>
        </main>

        {/* Footer */}
        <footer>
          {/* Footer */}
        </footer>
      </div>
    );
  }
  `;
}

// ============================================================================
// EJEMPLO 10: Filtrar testimonios por categoría
// ============================================================================

export function Example10_FilterByTag() {
  return `
  import { useState } from 'react';
  import { CarouselContainer, useCarouselTestimonials } from '@/features/carrusel';
  import type { CarouselTestimonial } from '@/features/carrusel';

  export function FilteredCarousel() {
    const { testimonials } = useCarouselTestimonials();
    const [selectedTag, setSelectedTag] = useState<string | null>(null);

    // Obtener todos los tags únicos
    const allTags = Array.from(
      new Set(testimonials.flatMap((t) => t.tags.map((tag) => tag.name)))
    );

    // Filtrar testimonios por tag
    const filtered = selectedTag
      ? testimonials.filter((t) => t.tags.some((tag) => tag.name === selectedTag))
      : testimonials;

    return (
      <div>
        <div className="tag-filters">
          <button
            onClick={() => setSelectedTag(null)}
            className={selectedTag === null ? 'active' : ''}
          >
            Todos
          </button>

          {allTags.map((tag) => (
            <button
              key={tag}
              onClick={() => setSelectedTag(tag)}
              className={selectedTag === tag ? 'active' : ''}
            >
              {tag}
            </button>
          ))}
        </div>

        <CarouselContainer testimonials={filtered} autoPlay={false} />
      </div>
    );
  }
  `;
}

// ============================================================================
// Tipos y Interfaces
// ============================================================================

/**
 * Estructura esperada de un testimonio en el carrusel
 */
export interface ExampleCarouselTestimonial extends CarouselTestimonial {
  id: number;
  fullName: string;
  testimonial: string;
  rating: number; // 0-10
  tags: Array<{ id: number; name: string }>;
  image?: string; // URL de imagen
  youtubeUrl?: string; // URL de video
  idEmbed?: number; // ID de video embed (YouTube)
}

/**
 * Respuesta esperada de la API
 */
export interface ExampleApiResponse {
  success: boolean;
  data: ExampleCarouselTestimonial[];
  message?: string;
}

export default {
  Example1_FullPage,
  Example2_EmbedInPage,
  Example3_ManualNavigation,
  Example4_LocalData,
  Example5_IndividualComponents,
  Example6_CustomInterval,
  Example7_DemoComponent,
  Example8_ReactiveCarousel,
  Example9_MainLayout,
  Example10_FilterByTag,
};
