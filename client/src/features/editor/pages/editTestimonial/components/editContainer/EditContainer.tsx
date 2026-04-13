import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './styles/edit-container.css';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import { getTestimonialService } from '../../../../services/testimonial/get-testimonial.service';
import SectionTitle from './SectionTitle';
import TagList from './TagList';
import MultimediaSection from './MultimediaSection';

const EditContainer = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [testimonial, setTestimonial] = useState<TestimonialResponseDTO | null>(
    null,
  );
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;
    getTestimonialService(Number(id))
      .then(setTestimonial)
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) return <p>Cargando...</p>;
  if (!testimonial) return <p>Testimonio no encontrado</p>;

  return (
    <div className='edit-container'>
      <div className='edit-form'>
        {/* Columna izquierda */}
        <div className='edit-left'>
          <section>
            <SectionTitle>1.- Categoría</SectionTitle>
            <select defaultValue={testimonial.category?.id ?? ''}>
              <option value=''>Seleccionar categoría</option>
            </select>
          </section>

          <section>
            <SectionTitle>2.- Etiquetas</SectionTitle>
            <TagList tags={testimonial.tags} />
            <select>
              <option value=''>Añadir tag</option>
            </select>
          </section>

          <section>
            <SectionTitle>3.- Testimonio</SectionTitle>
            <label className='input-label'>Testimonio</label>
            <textarea defaultValue={testimonial.testimonial} rows={5} />
          </section>
        </div>

        {/* Columna derecha */}
        <div className='edit-right'>
          <SectionTitle>4.- Multimedia</SectionTitle>

          <MultimediaSection
            title='Mostrar video en el testimonio'
            name='video'
            type='video'
            mediaUrl={testimonial.media?.videoUrl}
            placeholder='Miniatura de video'
          />

          <MultimediaSection
            title='Mostrar imagen en el testimonio'
            name='image'
            type='image'
            mediaUrl={testimonial.media?.imageUrl}
            placeholder='Miniatura de imagen'
          />
        </div>
      </div>

      <div className='edit-actions'>
        <button className='btn-cancel' onClick={() => navigate(-1)}>
          CANCELAR
        </button>
        <button
          className='btn-save'
          onClick={() => console.log('Guardar', testimonial)}
        >
          GUARDAR CAMBIOS
        </button>
      </div>
    </div>
  );
};

export default EditContainer;
