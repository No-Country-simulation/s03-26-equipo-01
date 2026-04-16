# Carrusel de Testimonios

Componente reutilizable de carrusel para mostrar testimonios de estudiantes con navegación fluida, indicadores visuales y auto-play opcional.

## Estructura

```
carrusel/
├── components/
│   ├── carousel-card/              # Tarjeta individual del testimonio
│   │   ├── CarouselCard.tsx
│   │   └── carousel-card.css
│   └── carousel-container/         # Contenedor con lógica de navegación
│       ├── CarouselContainer.tsx
│       └── carousel-container.css
├── models/
│   └── carouselTestimonial.d.ts   # Tipos y interfaces
├── services/
│   └── testimonials.service.ts    # Llamadas a API
├── adapters/
│   └── testimonial.adapter.ts     # Transformación de datos
├── hooks/
│   └── useCarouselTestimonials.ts # Hook para obtener testimonios
├── pages/
│   ├── CarouselPage.tsx           # Página completa del carrusel
│   └── carousel-page.css
└── index.ts                        # Exports principales
```

## Componentes

### CarouselCard

Tarjeta individual que muestra un testimonio con:

- Nombre y calificación del autor
- Descripción del testimonio (máximo 4 líneas)
- Tags relacionados
- Imagen o video (si está disponible)

```tsx
import { CarouselCard } from '@/features/carrusel';

<CarouselCard testimonial={testimonial} />;
```

### CarouselContainer

Contenedor del carrusel con navegación. Acepta:

- `testimonials`: Array de testimonios
- `autoPlay`: Activar reproducción automática (default: false)
- `autoPlayInterval`: Intervalo en ms (default: 5000)

```tsx
import { CarouselContainer } from '@/features/carrusel';

<CarouselContainer
  testimonials={testimonials}
  autoPlay={true}
  autoPlayInterval={5000}
/>;
```

### CarouselPage

Página completa lista para usar en rutas.

```tsx
import { CarouselPage } from '@/features/carrusel';

// En tu archivo de rutas
<Route path='/testimonios' element={<CarouselPage />} />;
```

## Hooks

### useCarouselTestimonials

Hook que obtiene testimonios publicados desde la API y los adapta.

```tsx
import { useCarouselTestimonials } from '@/features/carrusel';

const { testimonials, isLoading, error } = useCarouselTestimonials();
```

## Características

✅ **Responsive**: Mobile-first, funciona en todos los tamaños de pantalla
✅ **Accesible**: Botones con aria-labels, navegación por teclado
✅ **Fluido**: Transiciones suaves con CSS
✅ **Medidas relativas**: rem, vh, vw - sin píxeles absolutos
✅ **CSS puro**: Sin librerías externas
✅ **Reutilizable**: Usa componentes existentes (RatingDisplay, TestimonialTags)
✅ **Auto-play**: Reproducción automática opcional con pausa al interactuar

## Patrones Usados

- **Arquitectura**: Separación de componentes, servicios, adapters y hooks
- **Tipado**: TypeScript con interfaces bien definidas
- **Estilos**: CSS puro con variables globales de `index.css`
- **Responsive**: Mobile-first con media queries
- **Performance**: Memoización de componentes, transiciones optimizadas

## Cómo Usar

### Opción 1: Página completa

```tsx
import { CarouselPage } from '@/features/carrusel';

function App() {
  return <CarouselPage />;
}
```

### Opción 2: Carrusel standalone

```tsx
import {
  CarouselContainer,
  useCarouselTestimonials,
} from '@/features/carrusel';

function MyComponent() {
  const { testimonials, isLoading } = useCarouselTestimonials();

  if (isLoading) return <p>Cargando...</p>;

  return <CarouselContainer testimonials={testimonials} autoPlay />;
}
```

### Opción 3: Con datos locales

```tsx
import { CarouselContainer } from '@/features/carrusel';

function Demo() {
  const testimonials = [
    {
      id: 1,
      fullName: 'Juan Pérez',
      testimonial: 'Excelente curso...',
      rating: 10,
      tags: [{ id: 1, name: 'React' }],
    },
    // más testimonios...
  ];

  return <CarouselContainer testimonials={testimonials} />;
}
```

## Estilos

El carrusel usa variables CSS globales de `index.css`:

- `--primary-color`: #FF9B71 (naranja)
- `--secondary-color`: #0F1845 (azul oscuro)
- `--secondary-color-text`: #4D433F (gris)
- `--medium-font`: Tamaño fluido para títulos
- `--primary-font`: Tamaño fluido para texto

Todas las medidas son relativas (rem, vh, vw) para una mejor adaptabilidad.

## Extensiones Futuras

- Filtros por categoría
- Búsqueda de testimonios
- Modo galería (grid)
- Animaciones personalizadas
- Swipe en móvil
