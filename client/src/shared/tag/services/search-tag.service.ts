import api from '../../../core/api/api';
import { SEARCH_TAG_API } from '../../../core/api/urls/urls';

const TAG_API_KEY = 'vza_9e579029dbc34f74b2ac170f6a3cf86f';

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
  if (!TAG_API_KEY)
    throw new Error('Falta configurar la API key para buscar tags');

  const payload: TagSearchRequest = {
    name,
  };

  const response = await api.post<TagSearchResponse[]>(
    SEARCH_TAG_API,
    payload,
    {
      headers: {
        'X-Api-Key': TAG_API_KEY,
      },
    },
  );
  return response.data;
}

export default searchTagService;
