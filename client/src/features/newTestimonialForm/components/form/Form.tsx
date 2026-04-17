import './styles/form.css';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import SubmitButton from '../submit-button/SubmitButton';
import { alpha } from '@mui/material/styles';
import { ImageUp, SquarePlay } from 'lucide-react';
import ComboBox, { type DataProps } from '../combo-box/ComboBox';
import { TextInput } from '../text-input/TextInput';
import AuthorizationCheckbox from '../authorization-checkbox/AuthorizationCheckbox';
import UploadButtonWithIcon from '../upload-button-with-icon/UploadButtonWithIcon';
import { TextInputWithIcon } from '../text-input-with-icon/TextInputWithIcon';
import { MultitextInput } from '../multitext-input/MultiTextInput';
import RatingPicker from '../rating-picker/RatingPicker';
import createTestimonial from '../../../../shared/testimonial/services/testimonial.service';
import type { Testimonial } from '../../../../shared/testimonial/models/testimonial';
import searchTagService from '../../../../shared/tag/services/search-tag.service';
import getEmbedApiKey from '../../../../shared/testimonial/services/embed-api-key.service';

const Form = () => {
  const [tagOptions, setTagOptions] = useState<DataProps[]>([]);
  const [tagSearch, setTagSearch] = useState('');
  const [isTagLoading, setIsTagLoading] = useState(false);

  const {
    control,
    formState: { isValid },
    handleSubmit,
    watch,
  } = useForm({
    mode: 'onChange',
    defaultValues: {
      fullName: '',
      email: '',
      testimonial: '',
      tagIds: [],
      authorization: false,
      youtubeUrl: '',
      image: null,
      rating: null,
    },
  });

  const selectedImage = watch('image');
  const youtubeUrl = watch('youtubeUrl');
  const [imagePreviewUrl, setImagePreviewUrl] = useState<string | null>(null);

  useEffect(() => {
    if (!selectedImage) {
      setImagePreviewUrl(null);
      return;
    }

    const objectUrl = URL.createObjectURL(selectedImage);
    setImagePreviewUrl(objectUrl);

    return () => URL.revokeObjectURL(objectUrl);
  }, [selectedImage]);

  useEffect(() => {
    const normalizedSearch = tagSearch.trim();

    if (!normalizedSearch) {
      return;
    }

    const timeoutId = window.setTimeout(async () => {
      try {
        setIsTagLoading(true);
        const tags = await searchTagService(normalizedSearch);
        setTagOptions((previousTags) => {
          const nextTags = [...previousTags];

          tags.forEach((tag) => {
            if (nextTags.some((item) => item.id === tag.id)) return;
            nextTags.push({ id: tag.id, label: tag.name });
          });

          return nextTags;
        });
      } catch (error) {
        console.error('Error al buscar tags:', error);
      } finally {
        setIsTagLoading(false);
      }
    }, 300);

    return () => window.clearTimeout(timeoutId);
  }, [tagSearch]);

  const onSubmit = async (data: Testimonial) => {
    try {
      const apiKey = getEmbedApiKey();
      await createTestimonial(data, apiKey || undefined);
      console.log('¡Testimonio guardado con éxito!');
    } catch (error) {
      console.error(error);
    }
  };

  const youtubeVideoId = getYoutubeVideoId(youtubeUrl);
  const youtubeThumbnailUrl = youtubeVideoId
    ? `https://img.youtube.com/vi/${youtubeVideoId}/hqdefault.jpg`
    : null;

  return (
    <section className='new-testimonial_form-container'>
      <form className='new-testimonial_form' onSubmit={handleSubmit(onSubmit)}>
        <TextInput
          name='fullName'
          label='Nombre y Apellido'
          control={control}
          placeholder='Usa nombre real'
          rules={{
            required: 'El nombre y apellido es necesario',
          }}
        />

        <TextInput
          name='email'
          label='Mail'
          control={control}
          placeholder='Registrado en la plataforma'
          rules={{
            required: 'El email es necesario',
          }}
        />

        <ComboBox
          control={control}
          data={tagOptions}
          label='Tags'
          name='tagIds'
          placeholder='Buscá y seleccioná tags'
          loading={isTagLoading}
          onSearch={setTagSearch}
          searchValue={tagSearch}
        />

        <RatingPicker
          name='rating'
          label='Valoración general'
          control={control}
          rules={{
            required: 'La valoración es necesaria',
          }}
        />

        <div className='full-row-grid'>
          <MultitextInput
            name='testimonial'
            label='Testimonio'
            control={control}
            rows={3}
            placeholder='Comparte tu experiencia'
            rules={{
              required: 'El testimonio es necesario',
            }}
          />
        </div>

        <div className='full-row-grid new-testimonial_media-grid'>
          <div className='new-testimonial_media-inputs'>
            <TextInputWithIcon
              control={control}
              label='Link video de Youtube (Opcional)'
              name='youtubeUrl'
              placeholder='https://www.youtube.com/watch...'
              icon={
                <SquarePlay className='icon' color={alpha('#2D2D2D', 0.5)} />
              }
            />

            <UploadButtonWithIcon
              control={control}
              label='Añadir imagen (Opcional)'
              name='image'
              icon={<ImageUp className='icon' color={alpha('#2D2D2D', 0.5)} />}
            />
          </div>

          {(youtubeThumbnailUrl || imagePreviewUrl) && (
            <div className='new-testimonial_media-previews'>
              {youtubeThumbnailUrl && (
                <div className='new-testimonial_preview-card'>
                  <span className='new-testimonial_preview-label'>
                    Vista previa del video
                  </span>
                  <a
                    className='new-testimonial_preview-link'
                    href={youtubeUrl}
                    target='_blank'
                    rel='noreferrer'
                  >
                    <img
                      className='new-testimonial_preview-image'
                      src={youtubeThumbnailUrl}
                      alt='Miniatura del video de YouTube'
                    />
                  </a>
                </div>
              )}

              {imagePreviewUrl && (
                <div className='new-testimonial_preview-card'>
                  <span className='new-testimonial_preview-label'>
                    Vista previa de la imagen
                  </span>
                  <img
                    className='new-testimonial_preview-image'
                    src={imagePreviewUrl}
                    alt='Vista previa de la imagen seleccionada'
                  />
                </div>
              )}
            </div>
          )}
        </div>

        <div className='full-row-grid'>
          <AuthorizationCheckbox
            name='authorization'
            control={control}
            text='Autorizo el uso público de mi testimonio en la plataforma y materiales de comunicación.'
            rules={{
              required: 'El curso o programa realizado es necesario',
            }}
          />
        </div>

        <div className='full-row-grid'>
          <SubmitButton isAvailable={isValid} />
        </div>
      </form>
    </section>
  );
};

function getYoutubeVideoId(url?: string) {
  if (!url) return null;

  try {
    const parsedUrl = new URL(url);

    if (parsedUrl.hostname.includes('youtu.be')) {
      return parsedUrl.pathname.slice(1) || null;
    }

    if (parsedUrl.hostname.includes('youtube.com')) {
      return parsedUrl.searchParams.get('v');
    }
  } catch {
    return null;
  }

  return null;
}

export default Form;
