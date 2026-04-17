import { useParams, useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';
import { CategorySection } from './CategorySection';
import { TagsSection } from './TagsSection';
import { TestimonialSection } from './TestimonialSection';
import { MultimediaSection } from './MultimediaSection';
import { useTagsEditor } from '../../hooks/use-editTestimonial';
import { useCategories } from '../../hooks/use-categories';
import { useLoadTestimonial } from '../../hooks/use-load-testimonial';
import { useSaveTestimonial } from '../../hooks/use-save-testimonial.tsx';
import Toast from '../../../../../../shared/components/toast/Toast';

const EditContainer = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const { activeTags, removedTags, removeTag, initTags, addTag } =
    useTagsEditor();

  const {
    testimonial,
    loading,
    category,
    testimonialText,
    showVideo,
    showImage,
    setCategory,
    setTestimonialText,
    setShowVideo,
    setShowImage,
  } = useLoadTestimonial(id, initTags);

  const {
    categories,
    loading: loadingCategories,
    searchByName,
  } = useCategories(category);

  const { toast, savingChanges, closeToast, handleSave } = useSaveTestimonial();

  const onSaveChanges = () => {
    if (!id) return;

    const payload = {
      id: Number(id),
      categoryId: category?.id || null,
      testimonial: testimonialText,
      tags: activeTags,
      removedTagIds: removedTags.map((t) => t.id),
      displayOptions: { showVideo, showImage },
    };

    handleSave(payload);
  };

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
            onClick={onSaveChanges}
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
