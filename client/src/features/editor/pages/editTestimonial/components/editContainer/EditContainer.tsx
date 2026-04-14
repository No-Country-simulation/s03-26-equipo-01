import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Button,
  MenuItem,
  Select,
  TextField,
  Chip,
  FormControl,
  InputLabel,
  Box,
  Typography,
  Radio,
  RadioGroup,
  FormControlLabel,
} from '@mui/material';
import type { TestimonialResponseDTO } from '../../../../models/testimonial-response';
import { getTestimonialService } from '../../../../services/testimonial/get-testimonial.service';

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
        {/* Columna izquierda */}
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 4 }}>
          <Box>
            <Typography variant='subtitle2' fontWeight={600} mb={1}>
              1.- Categoría
            </Typography>
            <FormControl fullWidth variant='standard'>
              <InputLabel>Seleccionar categoría</InputLabel>
              <Select defaultValue={testimonial.category?.id ?? ''}>
                <MenuItem value=''>Seleccionar categoría</MenuItem>
              </Select>
            </FormControl>
          </Box>

          <Box>
            <Typography variant='subtitle2' fontWeight={600} mb={1}>
              2.- Etiquetas
            </Typography>
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 1, mb: 1 }}>
              {testimonial.tags.map((tag) => (
                <Chip
                  key={tag.id}
                  label={tag.name}
                  onDelete={() => {}}
                  size='small'
                />
              ))}
            </Box>
            <FormControl fullWidth variant='standard'>
              <InputLabel>Añadir tag</InputLabel>
              <Select value=''>
                <MenuItem value=''>Añadir tag</MenuItem>
              </Select>
            </FormControl>
          </Box>

          <Box>
            <Typography variant='subtitle2' fontWeight={600} mb={1}>
              3.- Testimonio
            </Typography>
            <Typography variant='caption' color='text.secondary'>
              Testimonio
            </Typography>
            <TextField
              fullWidth
              multiline
              rows={5}
              variant='standard'
              defaultValue={testimonial.testimonial}
            />
          </Box>
        </Box>

        {/* Columna derecha */}
        <Box>
          <Typography variant='subtitle2' fontWeight={600} mb={3}>
            4.- Multimedia
          </Typography>

          <Box
            sx={{
              display: 'grid',
              gridTemplateColumns: '1fr 140px',
              gap: 2,
              mb: 4,
              alignItems: 'start',
            }}
          >
            <Box>
              <Typography variant='body2' mb={1}>
                Mostrar video en el testimonio
              </Typography>
              <RadioGroup defaultValue='no'>
                <FormControlLabel
                  value='si'
                  control={<Radio size='small' />}
                  label='Sí'
                />
                <FormControlLabel
                  value='no'
                  control={<Radio size='small' />}
                  label='No'
                />
              </RadioGroup>
              {testimonial.media?.videoUrl && (
                <Typography
                  variant='caption'
                  color='primary'
                  component='a'
                  href={testimonial.media.videoUrl}
                  target='_blank'
                >
                  {testimonial.media.videoUrl}
                </Typography>
              )}
            </Box>
            <Box
              sx={{
                width: 140,
                height: 140,
                bgcolor: '#f9f9f9',
                border: '1px solid #eee',
                borderRadius: 1,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
              }}
            >
              <Typography
                variant='caption'
                color='text.secondary'
                align='center'
              >
                Miniatura de video
              </Typography>
            </Box>
          </Box>

          <Box
            sx={{
              display: 'grid',
              gridTemplateColumns: '1fr 140px',
              gap: 2,
              alignItems: 'start',
            }}
          >
            <Box>
              <Typography variant='body2' mb={1}>
                Mostrar imagen en el testimonio
              </Typography>
              <RadioGroup defaultValue='no'>
                <FormControlLabel
                  value='si'
                  control={<Radio size='small' />}
                  label='Sí'
                />
                <FormControlLabel
                  value='no'
                  control={<Radio size='small' />}
                  label='No'
                />
              </RadioGroup>
            </Box>
            <Box
              sx={{
                width: 140,
                height: 140,
                bgcolor: '#f9f9f9',
                border: '1px solid #eee',
                borderRadius: 1,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                overflow: 'hidden',
              }}
            >
              {testimonial.media?.imageUrl ? (
                <img
                  src={testimonial.media.imageUrl}
                  alt='thumbnail'
                  style={{ width: '100%', height: '100%', objectFit: 'cover' }}
                />
              ) : (
                <Typography
                  variant='caption'
                  color='text.secondary'
                  align='center'
                >
                  Miniatura de imagen
                </Typography>
              )}
            </Box>
          </Box>
        </Box>
      </Box>

      {/* Acciones */}
      <Box sx={{ display: 'flex', justifyContent: 'center', gap: 3, mt: 4 }}>
        <Button
          variant='outlined'
          onClick={() => navigate(-1)}
          sx={{ px: 6, borderColor: '#1a237e', color: '#1a237e' }}
        >
          CANCELAR
        </Button>
        <Button variant='contained' sx={{ px: 6, bgcolor: '#1a237e' }}>
          GUARDAR CAMBIOS
        </Button>
      </Box>
    </Box>
  );
};

export default EditContainer;
