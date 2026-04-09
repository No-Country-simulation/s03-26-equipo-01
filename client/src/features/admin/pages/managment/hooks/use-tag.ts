import { useEffect, useState } from "react";
import type {  CreatedTag } from "../../../adapters/tag/dtos/create-tag";
import type { Tag } from "../../../models/tag";
import useApi from "../../../../../core/api/hooks/use-api";
import addTagService from "../../../services/tag/add-tag.service";
import getAllTags from "../../../services/tag/get-all-tags.service";
import editTagService from "../../../services/tag/edit-tag-service";
import deleteTagService from "../../../services/tag/delete-tag.service";

const useTag = () => {

    const {post, put, deleted, get} = useApi<Tag>()
    const [tags, setTags] = useState<Tag[]>([]);
    const [tag, setTag] = useState<Tag | null>(null);

    useEffect(() => {
        get<Tag[]>(getAllTags)
            .then(newsTag => setTags(newsTag))
            .catch(error => console.log(error))
    }, [tag]);

    const addTag = async (tag: CreatedTag) => {
        const newTag = await post<CreatedTag>(addTagService, tag);
        setTag(newTag);
        return newTag;
    }

    const editTag = async (tag: CreatedTag, id: number | undefined): Promise<Tag> => {
        const editTag = await put<CreatedTag>(editTagService, tag, id);
        setTag(editTag);
        return editTag;
    }

    const deleteTag = async (id?: number): Promise<void> => {
        await deleted(deleteTagService, id);
        setTag(null);
    }

    return {tags, addTag, editTag, deleteTag}
}

export default useTag;
