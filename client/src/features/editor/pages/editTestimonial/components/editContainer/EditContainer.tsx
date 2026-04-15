import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import { getTestimonialService } from '../../../../services/testimonial/get-testimonial.service';
import { CategorySection } from './CategorySection';
import { TagsSection } from './TagsSection';
import { TestimonialSection } from './TestimonialSection';
import { MultimediaSection } from './MultimediaSection';
import { useTagsEditor } from '../../hooks/use-editTestimonial';

const EditContainer = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [testimonial, setTestimonial] = useState<TestimonialResponseDTO | null>(
    null,
  );
  const [loading, setLoading] = useState(true);

  const { activeTags, removedTags, removeTag, initTags } = useTagsEditor();

  useEffect(() => {
    if (!id) return;
    getTestimonialService(Number(id))
      .then((data) => {
        setTestimonial(data);
        initTags(data.tags);
      })
      .finally(() => setLoading(false));
  }, [id]);

  const handleSave = () => {
    const removedTagIds = removedTags.map((t) => t.id);
    console.log('Tags eliminados:', removedTagIds);
    // updateTestimonialService(Number(id), { removedTagIds });
  };

  if (loading) return <p>Cargando...</p>;
  if (!testimonial) return <p>Testimonio no encontrado</p>;

  return (
    <Box
      sx={{
        background: '#fff',
        borderRadius: 3,
        border: '1px solid #f0f0f0',
        boxShadow: '0 10px 30px rgba(0,0,0,0.05)',
        p: 6,
      }}
    >
      <Box sx={{ display: 'grid', gridTemplateColumns: '1.2fr 1fr', gap: 10 }}>
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 4 }}>
          <CategorySection defaultValue={testimonial.category} />
          <TagsSection tags={activeTags} onRemoveTag={removeTag} />
          <TestimonialSection defaultValue={testimonial.testimonial} />
        </Box>

        <Box>
          <MultimediaSection media={testimonial.media} />
        </Box>
      </Box>

      <Box sx={{ display: 'flex', justifyContent: 'center', gap: 3, mt: 4 }}>
        <Button
          variant='outlined'
          onClick={() => navigate(-1)}
          sx={{ px: 6, borderColor: '#1a237e', color: '#1a237e' }}
        >
          CANCELAR
        </Button>
        <Button
          variant='contained'
          onClick={handleSave}
          sx={{ px: 6, bgcolor: '#1a237e' }}
        >
          GUARDAR CAMBIOS
        </Button>
      </Box>
    </Box>
  );
};

export default EditContainer;
