import type { UseFormRegister } from "react-hook-form"
import type { CategoryCreated } from "../../adapters/category/dtos/category-created"

export interface CategoryModalFormProps {
    onSubmit: (categoryCreated: CategoryCreated) => void
}

export interface InputsContainerProps {
    register: UseFormRegister<CategoryCreated>
    errorMessage?: string
}

export interface ButtonsContainerProps {
    onActive: () => void
}