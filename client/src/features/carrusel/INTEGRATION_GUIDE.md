# Guía de Integración - Carrusel de Testimonios

## Resumen Ejecutivo

Se ha creado un carrusel de testimonios completamente funcional siguiendo la arquitectura y buenas prácticas del proyecto CMS. El componente es:

✅ **Reutilizable**: Usa componentes existentes del proyecto
✅ **Responsive**: Mobile-first con medidas relativas
✅ **CSS Puro**: Sin dependencias externas adicionales
✅ **Accesible**: Navegación por teclado y aria-labels
✅ **Fluido**: Transiciones suaves y auto-play opcional

## Estructura Creada

```
features/carrusel/
├── components/
│   ├── carousel-card/              → Tarjeta individual (reutiliza RatingDisplay, TestimonialTags)
│   ├── carousel-container/         → Lógica de navegación
│   └── carousel-demo/              → Componente para testing
├── models/                          → Tipos TypeScript
├── services/                        → Llamadas API
├── adapters/                        → Transformación de datos
├── hooks/                           → useCarouselTestimonials
├── pages/                           → CarouselPage (página completa)
└── README.md
```

## Instalación / Integración

### Paso 1: Verificar Endpoints de API

Asegúrate de que tu API proporcione estos endpoints:

```
GET /testimonials/published      → Testimonios publicados
GET /testimonials/public         → Testimonios públicos
```

**Respuesta esperada:**

```json
[
  {
    "id": 1,
    "fullName": "María García",
    "testimonial": "Contenido...",
    "rating": 10,
    "tags": [{ "id": 1, "name": "React" }],
    "image": "url...",
    "youtubeUrl": "url...",
    "idEmbed": 123456
  }
]
```

### Paso 2: Integrar en Rutas (router)

**Opción A: Página dedicada a testimonios**

```tsx
// En tu archivo de rutas (ej: routes.ts)
import { CarouselPage } from '@/features/carrusel';

export const routes = [
  // ... otras rutas
  {
    path: '/testimonios',
    element: <CarouselPage />,
  },
];
```

**Opción B: Componente en página existente**

```tsx
// En cualquier página
import {
  CarouselContainer,
  useCarouselTestimonials,
} from '@/features/carrusel';

export function HomePage() {
  const { testimonials, isLoading } = useCarouselTestimonials();

  return (
    <div>
      {/* Otros contenidos... */}
      {!isLoading && testimonials.length > 0 && (
        <CarouselContainer testimonials={testimonials} autoPlay />
      )}
    </div>
  );
}
```

### Paso 3: Personalización Opcional

#### Cambiar Intervalos de Auto-play

```tsx
<CarouselContainer
  testimonials={testimonials}
  autoPlay={true}
  autoPlayInterval={3000} // Cada 3 segundos
/>
```

#### Usar Componentes Independientes

```tsx
import { CarouselCard } from '@/features/carrusel';

// Para mostrar solo una tarjeta
<CarouselCard testimonial={testimonialData} />;
```

## Testing

### Componente Demo

Para probar el carrusel con datos de demostración sin necesidad de API:

```tsx
import { CarouselDemo } from '@/features/carrusel';

// En tu componente o página
<CarouselDemo autoPlay={true} />;
```

El demo incluye 5 testimonios de ejemplo con diferentes configuraciones.

### Checklist de Testing

- [ ] Navegar con botones anterior/siguiente
- [ ] Hacer clic en los puntos indicadores
- [ ] Verificar auto-play
- [ ] Probar en móvil (responsive)
- [ ] Verificar accesibilidad (Tab, Enter)
- [ ] Comprobar que se cargan imágenes y videos
- [ ] Validar carga de API en red

## Arquitectura y Patrones

### Separación de Responsabilidades

1. **Componentes** (`components/`)
   - `CarouselCard`: Presentación de datos
   - `CarouselContainer`: Lógica de navegación

2. **Servicios** (`services/`)
   - Llamadas HTTP a la API

3. **Adapters** (`adapters/`)
   - Transformación de datos de API al modelo local

4. **Hooks** (`hooks/`)
   - `useCarouselTestimonials`: Orquesta servicio + adapter

5. **Pages** (`pages/`)
   - `CarouselPage`: Página completa lista para rutas

### Componentes Reutilizados del Proyecto

- `RatingDisplay` → Muestra calificación con estrella
- `TestimonialTags` → Renderiza tags con estilos del proyecto
- Estilos globales de `index.css` → Variables de color, tipografía fluida

### Medidas y Responsive

**Unidades usadas:**

- `rem` → Espaciado, padding, gap
- `vh`/`vw` → Tamaños de fuente (fluidos con clamp)
- `%` → Ancho de contenedores
- Media queries → Mobile-first: 640px, 768px, 1024px

**Breakpoints:**

- `< 640px` → Mobile
- `640px - 768px` → Tablet pequeño
- `768px - 1024px` → Tablet grande
- `> 1024px` → Desktop

## CSS Personalización

### Variables CSS Globales Usadas

```css
--primary-color: #ff9b71 /* Naranja */ --secondary-color: #0f1845
  /* Azul oscuro */ --secondary-color-text: #4d433f /* Gris */
  --light-background: #fafafa /* Fondo claro */ --primary-font: clamp(...)
  /* Tipografía fluida */ --medium-font: clamp(...)
  --medium-font-title: clamp(...) --large-font: clamp(...);
```

### Personalizar Colores (Opcional)

En el archivo CSS del carrusel, busca:

```css
.carousel-button {
  background-color: var(--primary-color); /* Cambiar aquí */
}

.carousel-dot.active {
  background-color: var(--primary-color); /* O aquí */
}
```

### Personalizar Transiciones

```css
.carousel-track {
  transition: transform 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  /* Cambiar duración (0.3s) o función de animación */
}
```

## Performance y Optimizaciones

- ✅ Transiciones con `transform` y `opacity` (GPU optimizadas)
- ✅ `will-change` en elemento `.carousel-track`
- ✅ Debounce de navegación con `isTransitioning`
- ✅ Limpieza de intervalos de auto-play
- ✅ Medidas relativas para mejor escalado

## Limitaciones Conocidas y Futuros Cambios

### Limitaciones Actuales

- No hay soporte para drag/swipe (diseño simple)
- Auto-play se pausa solo con navegación, no con hover
- Cargas de imágenes no están optimizadas (sin lazy loading)

### Mejoras Futuras Posibles

- Agregar swipe en móvil
- Lazy loading de imágenes
- Filtros por categoría/tag
- Modo galería (grid)
- Paginación alternativa
- Animaciones configurables

## Solución de Problemas

### No se cargan los testimonios

1. Verifica que el endpoint `/testimonials/published` exista en tu API
2. Abre la consola (F12) para ver errores de red
3. Usa el componente `CarouselDemo` para verificar que el UI funciona

### Estilos no se aplican correctamente

1. Asegúrate de que los imports de CSS estén presentes
2. Verifica que `index.css` esté importado en `main.tsx`
3. Comprueba que no haya conflictos CSS con otros componentes

### Auto-play se detiene sin motivo

1. Es normal que se pause cuando el usuario navega
2. Usa los puntos indicadores para reactivar el auto-play

### Responsive no funciona bien

1. Prueba en modo responsive del navegador (F12)
2. Verifica los media queries en `carousel-container.css`
3. Asegúrate de que el contenedor padre tenga `width: 100%`

## Ficheros Clave

| Archivo                      | Propósito                         |
| ---------------------------- | --------------------------------- |
| `CarouselCard.tsx`           | Renderiza tarjeta individual      |
| `CarouselContainer.tsx`      | Lógica de navegación y auto-play  |
| `useCarouselTestimonials.ts` | Hook para obtener datos           |
| `testimonials.service.ts`    | Llamadas API                      |
| `testimonial.adapter.ts`     | Transformación de datos           |
| `carousel-container.css`     | Estilos del carrusel (navegación) |
| `carousel-card.css`          | Estilos de la tarjeta             |

## Contacto y Soporte

Si encuentras issues o tienes sugerencias de mejora:

1. Verifica el README.md en la carpeta `carrusel/`
2. Revisa los comentarios en el código
3. Consulta los datos de prueba en `CarouselDemo.tsx`
