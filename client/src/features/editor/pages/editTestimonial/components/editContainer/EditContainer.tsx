import { useEffect, useState } from 'react';
import type { ReactNode } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import type { Category } from '../../../../../admin/models/category';

import { getTestimonialService } from '../../../../services/testimonial/get-testimonial.service';
import { updateTestimonialService } from '../../services/update-testimonial.service';
import { CategorySection } from './CategorySection';
import { TagsSection } from './TagsSection';
import { TestimonialSection } from './TestimonialSection';
import { MultimediaSection } from './MultimediaSection';
import { useTagsEditor } from '../../hooks/use-editTestimonial';
import { useCategories } from '../../hooks/use-categories';
import { useTrackChanges } from '../../hooks/use-track-changes';
import Toast from '../../../../../../shared/components/toast/Toast';

interface ToastState {
  content: ReactNode;
  type: 'success' | 'error';
}

const EditContainer = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const [testimonial, setTestimonial] = useState<TestimonialResponseDTO | null>(
    null,
  );
  const [loading, setLoading] = useState(true);
  const [savingChanges, setSavingChanges] = useState(false);
  const [toast, setToast] = useState<ToastState | null>(null);

  const [category, setCategory] = useState<Category | null>(null);
  const [testimonialText, setTestimonialText] = useState('');
  const [showVideo, setShowVideo] = useState(false);
  const [showImage, setShowImage] = useState(false);

  const {
    categories,
    loading: loadingCategories,
    searchByName,
  } = useCategories(category);

  const { activeTags, removedTags, removeTag, initTags, addTag } =
    useTagsEditor();

  const { setInitialValues, getChanges } = useTrackChanges();

  useEffect(() => {
    if (!id) return;

    getTestimonialService(Number(id))
      .then((data) => {
        setTestimonial(data);
        initTags(data.tags);
        setCategory(data.category || null);
        setTestimonialText(data.testimonial || '');
        setShowVideo(false);
        setShowImage(false);
        // Registrar los valores iniciales para rastrear cambios
        setInitialValues(
          data.category || null,
          data.testimonial || '',
          false,
          false,
          data.tags,
        );
      })
      .finally(() => setLoading(false));
  }, [id, initTags, setInitialValues]);

  const handleSave = async () => {
    if (!id) return;

    setSavingChanges(true);

    const payload = {
      id: Number(id),
      categoryId: category?.id || null,
      testimonial: testimonialText,
      tags: activeTags,
      removedTagIds: removedTags.map((t) => t.id),
      displayOptions: { showVideo, showImage },
    };

    // Obtener los cambios realizados
    const changes = getChanges(
      category,
      testimonialText,
      showVideo,
      showImage,
      activeTags,
      removedTags,
    );

    console.log('Cambios detectados:', changes);
    console.log('Payload preparado:', payload);

    try {
      const response = await updateTestimonialService(payload);
      console.log('Testimonio actualizado exitosamente:', response);
      setToast({
        type: 'success',
        content: (
          <>
            <strong style={{ display: 'block', marginBottom: '4px' }}>
              ¡Éxito!
            </strong>
            <span>Los cambios se han guardado correctamente.</span>
          </>
        ),
      });
      setTimeout(() => navigate('/editor/testimonials'), 1500);
    } catch (error) {
      console.error('Error al guardar los cambios:', error);
      setToast({
        type: 'error',
        content: <span>Hubo un error al actualizar los cambios.</span>,
      });
    } finally {
      setSavingChanges(false);
    }
  };

  const closeToast = () => setToast(null);

  if (loading) return <p>Cargando...</p>;
  if (!testimonial) return <p>Testimonio no encontrado</p>;

  return (
    <>
      {toast && (
        <Toast content={toast.content} type={toast.type} onClose={closeToast} />
      )}
      <Box
        sx={{
          background: '#fff',
          borderRadius: 3,
          border: '1px solid #f0f0f0',
          boxShadow: '0 10px 30px rgba(0,0,0,0.05)',
          p: 6,
        }}
      >
        <Box
          sx={{ display: 'grid', gridTemplateColumns: '1.2fr 1fr', gap: 10 }}
        >
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 4 }}>
            {/* Ahora las variables existen porque vienen del hook useCategories arriba */}
            <CategorySection
              value={category}
              options={categories}
              loading={loadingCategories}
              onSearch={(q) => searchByName(q)}
              onChange={(val) => setCategory(val)}
            />

            <TagsSection
              tags={activeTags}
              onRemoveTag={removeTag}
              onAddTag={addTag}
              testimonialId={Number(id)}
            />
            <TestimonialSection
              value={testimonialText}
              onChange={(val: string) => setTestimonialText(val)}
            />
          </Box>

          <Box>
            <MultimediaSection
              media={testimonial.media}
              showVideo={showVideo}
              showImage={showImage}
              onVideoChange={(val: boolean) => setShowVideo(val)}
              onImageChange={(val: boolean) => setShowImage(val)}
            />
          </Box>
        </Box>

        <Box sx={{ display: 'flex', justifyContent: 'center', gap: 3, mt: 4 }}>
          <Button
            variant='outlined'
            onClick={() => navigate(-1)}
            disabled={savingChanges}
            sx={{ px: 6, borderColor: '#1a237e', color: '#1a237e' }}
          >
            CANCELAR
          </Button>
          <Button
            variant='contained'
            onClick={handleSave}
            disabled={savingChanges}
            sx={{ px: 6, bgcolor: '#1a237e' }}
          >
            {savingChanges ? 'GUARDANDO...' : 'GUARDAR CAMBIOS'}
          </Button>
        </Box>
      </Box>
    </>
  );
};

export default EditContainer;
