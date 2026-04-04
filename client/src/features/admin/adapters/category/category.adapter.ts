import type { Category } from "../../models/category";
import type { CategoryResponse } from "./dtos/response";


export function categoriesAdapter(response: CategoryResponse[]): Category[] {
    return response.map(category => categoryAdapter(category));
}

export function categoryAdapter(categoryResponse: CategoryResponse): Category {
    return {
        id: categoryResponse.id,
        name: categoryResponse.name
    }
}