import type { CreatedTag } from "../../adapters/tag/dtos/create-tag";

export interface TagModalFormProps {
    onSubmit: (tag: CreatedTag) => void
}