import { useState } from "react"
import type { CategoryCreated } from "../../../adapters/category/dtos/category-created"
import type { Category } from "../../../models/category";

const useCategory = () => {
    
    const [category, setCategory] = useState<CategoryCreated | null>(null);

    const addCategory = (category: CategoryCreated) => setCategory(category);
    const editCategory = (category: Category) => setCategory(category);
    const deleteCategory = (category: Category) => setCategory(category);

    return {category, addCategory, editCategory, deleteCategory}
}

export default useCategory;