import { useEffect, useState } from "react";
import type {  CreatedTag } from "../../../adapters/tag/dtos/create-tag";
import type { Tag } from "../../../models/tag";
import useApi from "../../../../../core/api/hooks/use-api";
import addTagService from "../../../services/tag/add-tag.service";
import getAllTags from "../../../services/tag/get-all-tags.service";

const useTag = () => {

    const {post, get} = useApi<Tag>()
    const [tag, setTag] = useState<CreatedTag | null>(null);
    const [tags, setTags] = useState<CreatedTag[]>([]);

    useEffect(() => {
        get<CreatedTag[]>(getAllTags)
            .then(newsTag => setTags(newsTag))
            .catch(error => console.log(error))
    }, [tag]);

    const addTag = async (tag: CreatedTag) => {
        const newTag = await post<CreatedTag>(addTagService, tag);
        setTag(newTag);
    }

    const editTag = (category: Tag) => setTag(category);
    const deleteTag = (category: Tag) => setTag(category);

    return {tag, tags, addTag, editTag, deleteTag}
}

export default useTag;