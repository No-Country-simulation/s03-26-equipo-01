# Debugging - Carrusel de Testimonios

## Cambios Realizados para Arreglar el Error

### El Problema
Error: `testimonials.map is not a function`
**Causa**: La API retorna testimonios pero el adapter no estaba mapeando correctamente los campos.

### Cambios Realizados

#### 1. **Adapter Actualizado** (`testimonial.adapter.ts`)
- ✅ Campo `witness` (API) → mapeado a `fullName` (modelo)
- ✅ Extrae imagen de `media.thumbnailUrl` o `media.imageUrl`
- ✅ Extrae video de `media.videoUrl` y `media.videoId`
- ✅ Maneja múltiples estructuras de respuesta (array directo, dentro de `data`, etc)
- ✅ Más robusto con try/catch y logs de debugging

#### 2. **Hook Mejorado** (`useCarouselTestimonials.ts`)
- ✅ Console.logs para ver qué llega de la API
- ✅ Mejor manejo de errores
- ✅ Debugging automático del tipo de datos

#### 3. **Estructura Esperada de la API**
```json
[
  {
    "id": 1,
    "witness": "María García",      // ← Campo del nombre (no fullName)
    "testimonial": "Contenido...",
    "rating": 10,
    "state": "Publicado",
    "tags": [
      { "id": 1, "name": "React" }
    ],
    "media": {
      "imageUrl": "...",
      "videoUrl": "...",
      "videoId": "dQw4w9WgXcQ",
      "thumbnailUrl": "..."
    }
  }
]
```

## Cómo Verificar que Funciona

### 1. Abrir la Consola del Navegador
- Presionar `F12` → Tab "Console"

### 2. Buscar estos Logs
Deberías ver something like:
```
Datos crudos de API: [Array(3)]
Tipo de datos: object
Es array: true
Datos adaptados: [Array(3)]
Cantidad de testimonios: 3
```

### 3. Si ves estos logs → ¡Funciona! ✅

### Si No Funciona

#### Si ves: `TypeError: testimonials.map is not a function`
**Significado**: Los datos no son un array
**Solución**: 
1. Revisar que `TESTIMONIAL_CARRUSEL` apunta a `/testimonial/published`
2. Asegurarse que el endpoint retorna un array
3. Revisar los logs de la API en el backend

#### Si ves: `No se encontró array de testimonios en la respuesta`
**Significado**: La estructura de respuesta es diferente a las esperadas
**Solución**: 
1. Copiar la estructura mostrada en los logs
2. Actualizar el adapter con la nueva estructura
3. Revisar la documentación de la API

#### Si ves: `Error al cargar testimonios`
**Significado**: Error de red o de API
**Solución**:
1. Abrir Red (Network) en Dev Tools
2. Buscar petición a `/testimonial/published`
3. Revisar status code (200 = ok, 4xx = error cliente, 5xx = error servidor)
4. Ver la respuesta exacta

## Estructura de Campos Mapeados

| Campo API | Campo Modelo | Notas |
|-----------|--------------|-------|
| `witness` | `fullName` | Nombre del autor |
| `testimonial` | `testimonial` | Contenido |
| `rating` | `rating` | Calificación (0-10) |
| `tags[].{id,name}` | `tags[].{id,name}` | Mantenido |
| `media.thumbnailUrl` \| `media.imageUrl` | `image` | URL de imagen |
| `media.videoUrl` | `youtubeUrl` | URL del video |
| `media.videoId` \| `idEmbed` | `idEmbed` | ID para embed |

## Próximos Pasos

1. ✅ Adapter actualizado para mapear campos correctamente
2. ✅ Hooks mejorado con logging
3. ⏭️ Revisar los logs en consola
4. ⏭️ Si aún hay errores, mostrar los logs aquí

## Comandos Útiles

### Ver respuesta de API en DevTools
```javascript
// En consola del navegador
fetch('/testimonial/published', {
  method: 'POST',
  headers: { 'X-Api-Key': 'vza_7adbad2802fa4c5c895f387d7843165b' }
}).then(r => r.json()).then(d => console.log(JSON.stringify(d, null, 2)))
```

### Limpiar datos del localStorage
```javascript
localStorage.clear()
```

## Archivo Clave

**Adapter**: [testimonial.adapter.ts](./adapters/testimonial.adapter.ts)
**Hook**: [useCarouselTestimonials.ts](./hooks/useCarouselTestimonials.ts)

## Contacto

Si los logs muestran algo diferente a lo esperado:
1. Copia los logs de consola
2. Copia la estructura de la respuesta de API (Network tab)
3. Comparte ambos para hacer un debugging más específico
