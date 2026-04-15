import api from '../../../../../core/api/api';
import { GET_ALL_CATEGORY_API } from '../../../../../core/api/urls/urls';
import type { Category } from '../../../../admin/models/category';

export async function getCategoriesService(): Promise<Category[]> {
  const response = await api.get<Category[]>(GET_ALL_CATEGORY_API);
  return response.data;
}
