import api from "../../../../core/api/api";
import { EDIT_TAG_API } from "../../../../core/api/urls/urls";
import type { CreatedTag } from "../../adapters/tag/dtos/create-tag";
import type { TagResponse } from "../../adapters/tag/dtos/response";
import { tagAdapter } from "../../adapters/tag/tag.adapter";
import type { Tag } from "../../models/tag";

async function editTagService(tag: CreatedTag, id?: number): Promise<Tag> {
    const newTag = await api.put<TagResponse>(`${EDIT_TAG_API}/${id}`, tag)
    return tagAdapter(newTag.data);
}

export default editTagService;