import type { Tag } from "../../models/tag";
import type { TagResponse } from "./dtos/response";

export function tagsAdapter(response: TagResponse[]): Tag[] {
    return response.map(tag => tagAdapter(tag));
}

export function tagAdapter(tag: TagResponse): Tag {
    return {
        id: tag.id,
        name: tag.name
    }
}