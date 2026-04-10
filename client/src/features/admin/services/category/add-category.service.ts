import api from "../../../../core/api/api";
import { ADD_CATEGORY_API } from "../../../../core/api/urls/urls";
import { categoryAdapter } from "../../adapters/category/category.adapter";
import type { CategoryCreated } from "../../adapters/category/dtos/category-created";
import type { CategoryResponse } from "../../adapters/category/dtos/response";
import type { Category } from "../../models/category";

async function addCategoryService(category: CategoryCreated): Promise<Category> {
    const newCategory = await api.post<CategoryResponse>(ADD_CATEGORY_API, category);
    return categoryAdapter(newCategory.data);
}

export default addCategoryService;