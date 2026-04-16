import api from '../../../../../core/api/api';
import { GET_CATEGORY_BY_NAME } from '../../../../../core/api/urls/urls';
import type { Category } from '../../../../admin/models/category';

export async function getCategoryByNameService(
  name?: string,
): Promise<Category[]> {
  const response = await api.get<Category[]>(GET_CATEGORY_BY_NAME, {
    params: name ? { name } : undefined,
  });
  return response.data;
}
