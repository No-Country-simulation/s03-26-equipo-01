import { useEffect, useState } from "react"
import type { Category } from "../../../models/category";
import useApi from "../../../../../core/api/hooks/use-api";
import type { CategoryCreated } from "../../../adapters/category/dtos/category-created";
import addCategoryService from "../../../services/category/add-category.service";
import editCategoryService from "../../../services/category/edit-category.service";
import deleteCategoryService from "../../../services/category/delete-category.service";
import getAllCategories from "../../../services/category/get-all-categories.service";

const useCategory = () => {

    const {post, put, deleted, get} = useApi<Category>()
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        get<Category[]>(getAllCategories)
            .then(newsCategories => setCategories(newsCategories))
            .catch(error => console.log(error))
    }, []);

    const addCategory = async (category: CategoryCreated) => {
        const newCategory = await post<CategoryCreated>(addCategoryService, category);
        setCategories(categories => [...categories, newCategory]);
        return newCategory;
    }

    const editCategory = async (category: CategoryCreated, id: number | undefined): Promise<Category> => {
        const editCategory = await put<CategoryCreated>(editCategoryService, category, id);
        setCategories(categories => categories.map(category => category.id === id ? editCategory : category));
        return editCategory;
    }

    const deleteCategory = async (id?: number): Promise<void> => {
        await deleted(deleteCategoryService, id);
        setCategories(categories => categories.filter(category => category.id !== id));
    }

    return {categories, addCategory, editCategory, deleteCategory}
}

export default useCategory;