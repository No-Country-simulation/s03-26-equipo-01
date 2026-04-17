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

    useEffect(() => {
        get<Tag[]>(getAllTags)
            .then(newsTag => setTags(newsTag))
            .catch(error => console.log(error))
    }, []);

    const addTag = async (tag: CreatedTag) => {
        const newTag = await post<CreatedTag>(addTagService, tag);
        setTags(prev => [...prev, newTag]);
        return newTag;
    }

    const editTag = async (tag: CreatedTag, id: number | undefined) => {
        const editTag = await put<CreatedTag>(editTagService, tag, id);
        setTags(prev => prev.map(tag => tag.id === editTag.id ? editTag : tag))
        return editTag;
    }

    const deleteTag = async (id: number) => {
        await deleted(deleteTagService, id);
        setTags(prev => removeTag(prev, id));
    }

    const removeTag = (tags: Tag[], id?: number) => tags.filter(tag => tag.id !== id)

    return {tags, addTag, editTag, deleteTag}
}

export default useTag;
