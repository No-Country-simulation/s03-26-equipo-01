import type { CategoryResponse } from "../../category/dtos/response"
import type { EditorResponse } from "../../editor/dtos/response"
import type { TagResponse } from "../../tag/dtos/response"

export interface TestimonialResourcesResponse {
    users: EditorResponse[]
    category: CategoryResponse[]
    states: string[]
    tags: TagResponse[]
}