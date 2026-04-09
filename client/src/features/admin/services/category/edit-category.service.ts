import api from "../../../../core/api/api";
import { EDIT_CATEGORY_API } from "../../../../core/api/urls/urls";
import { categoryAdapter } from "../../adapters/category/category.adapter";
import type { CategoryCreated } from "../../adapters/category/dtos/category-created";
import type { CategoryResponse } from "../../adapters/category/dtos/response";
import type { Category } from "../../models/category";

async function editCategoryService(category: CategoryCreated, id?: number): Promise<Category> {
    const newTag = await api.patch<CategoryResponse>(`${EDIT_CATEGORY_API}/${id}`, category)
    return categoryAdapter(newTag.data);
}

export default editCategoryService;