import type { UseFormRegister } from "react-hook-form"
import type { Category } from "../../models/category"
import type { CategoryCreated } from "./model/category-created"

export interface CategoryModalFormProps {
    onSubmit: (categoryCreated: CategoryCreated) => Category
}

export interface InputsContainerProps {
    register: UseFormRegister<CategoryCreated>
    errorMessage?: string
}

export interface ButtonsContainerProps {
    onActive: () => void
}