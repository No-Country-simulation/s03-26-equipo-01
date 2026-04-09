import api from "../../../../core/api/api";
import { GET_ALL_CATEGORY_API } from "../../../../core/api/urls/urls";
import { categoriesAdapter } from "../../adapters/category/category.adapter";
import type { CategoryResponse } from "../../adapters/category/dtos/response";
import type { Category } from "../../models/category";

async function getAllCategories(): Promise<Category[]> {
    const categoiresResponse = await api.get<CategoryResponse[]>(GET_ALL_CATEGORY_API);
    return categoriesAdapter(categoiresResponse.data);
}

export default getAllCategories;