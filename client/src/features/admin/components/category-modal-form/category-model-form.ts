import type { CategoryCreated } from "../../adapters/category/dtos/category-created"

export interface CategoryModalFormProps {
    onSubmit: (categoryCreated: CategoryCreated) => void
}