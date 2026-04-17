import api from '../../../core/api/api';
import { SEARCH_TAG_API } from '../../../core/api/urls/urls';
import getEmbedApiKey from '../../testimonial/services/embed-api-key.service';

type TagSearchRequest = {
  name: string;
};

export type TagSearchResponse = {
  id: number;
  name: string;
  isActive: boolean;
  createdAt: string;
};

async function searchTagService(name: string): Promise<TagSearchResponse[]> {
  const tagApiKey = getEmbedApiKey();

  if (!tagApiKey)
    throw new Error('Falta configurar la API key para buscar tags');

  const payload: TagSearchRequest = {
    name,
  };

  const response = await api.post<TagSearchResponse[]>(
    SEARCH_TAG_API,
    payload,
    {
      headers: {
        'X-Api-Key': tagApiKey,
      },
    },
  );
  return response.data;
}

export default searchTagService;
