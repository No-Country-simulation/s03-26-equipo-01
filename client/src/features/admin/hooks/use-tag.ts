import { useState } from "react";
import type {  CreatedTag } from "../adapters/tag/dtos/create-tag";
import type { Tag } from "../models/tag";

const useTag = () => {
    const [category, setCategory] = useState<CreatedTag | null>(null);

    const addTag = (category: CreatedTag) => setCategory(category);
    const editTag = (category: Tag) => setCategory(category);
    const deleteTag = (category: Tag) => setCategory(category);

    return {category, addTag, editTag, deleteTag}
}

export default useTag;