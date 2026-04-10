import api from "../../../../core/api/api";
import { GET_ALL_TAG_API } from "../../../../core/api/urls/urls";
import type { TagResponse } from "../../adapters/tag/dtos/response";
import { tagsAdapter } from "../../adapters/tag/tag.adapter";
import type { Tag } from "../../models/tag";

async function getAllTags(): Promise<Tag[]> {
    const tagsResponse = await api.get<TagResponse[]>(GET_ALL_TAG_API);
    return tagsAdapter(tagsResponse.data);
}

export default getAllTags;