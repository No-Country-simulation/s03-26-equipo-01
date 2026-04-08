import { useState } from "react";
import type {  CreatedTag } from "../../../adapters/tag/dtos/create-tag";
import type { Tag } from "../../../models/tag";
import useApi from "../../../../../core/api/hooks/use-api";
import addTagService from "../../../services/tag/add-tag-service";

const useTag = () => {

    const {post} = useApi<Tag>()
    const [tag, setTag] = useState<CreatedTag | null>(null);

    const addTag = async (tag: CreatedTag) => {
        const newTag = await post<CreatedTag>(addTagService, tag);
        setTag(newTag);
    }

    const editTag = (category: Tag) => setTag(category);
    const deleteTag = (category: Tag) => setTag(category);

    return {tag, addTag, editTag, deleteTag}
}

export default useTag;