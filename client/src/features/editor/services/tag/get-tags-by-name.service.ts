import api from '../../../../core/api/api';
import { GET_TAGS_BY_NAME } from '../../../../core/api/urls/urls';
import type { Tag } from '../../../admin/models/tag';

export async function getTagsByNameService(
  name: string,
  testimonialId: number,
): Promise<Tag[]> {
  const response = await api.post<Tag[]>(GET_TAGS_BY_NAME, {
    name,
    testimonialId,
  });
  return response.data;
}
