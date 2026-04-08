import api from "../../../../core/api/api";
import { ADD_TAG_API } from "../../../../core/api/urls/urls";
import type { CreatedTag } from "../../adapters/tag/dtos/create-tag";
import type { TagResponse } from "../../adapters/tag/dtos/response";
import { tagAdapter } from "../../adapters/tag/tag.adapter";
import type { Tag } from "../../models/tag";

async function addTagService(tag: CreatedTag): Promise<Tag> {
    const newTag = await api.post<TagResponse>(ADD_TAG_API, tag);
    console.log(newTag)
    return tagAdapter(newTag.data);
}

export default addTagService;