import type { CategoryResponse } from "../../category/dtos/response"
import type { EditorResponse } from "../../editor/dtos/response"

export interface TestimonialResourcesResponse {
    users: EditorResponse[]
    category: CategoryResponse[]
    states: string[]
}