import { useEffect, useState } from "react";
import type {  CreatedTag } from "../../../adapters/tag/dtos/create-tag";
import type { Tag } from "../../../models/tag";
import useApi from "../../../../../core/api/hooks/use-api";
import addTagService from "../../../services/tag/add-tag.service";
import getAllTags from "../../../services/tag/get-all-tags.service";

const useTag = () => {

    const {post, get} = useApi<Tag>()
    const [tags, setTags] = useState<CreatedTag[]>([]);

    useEffect(() => {
        get<CreatedTag[]>(getAllTags)
            .then(newsTag => setTags(newsTag))
            .catch(error => console.log(error))
    }, []);

    const addTag = async (tag: CreatedTag) => {
        const newTag = await post<CreatedTag>(addTagService, tag);
        setters(newTag);
    }

    const editTag = (category: Tag) => console.log(category);
    const deleteTag = (category: Tag) => console.log(category);

    const setters = (newTag: CreatedTag) => {
        setTags(tags => {
            tags.push(newTag)
            return tags
        });
    }

    return {tags, addTag, editTag, deleteTag}
}

export default useTag;