import type { Image } from "../../../models/image"
import type { MediaResponse } from "../../media/dtos/media"
import type { AdminState } from "../../../models/state"
import type { CategoryResponse } from "../../category/dtos/response"
import type { TagResponse } from "../../tag/dtos/response"

export interface TestimonialResponse {
    id: number, 
    testimonial: string 
    idEmbed: number 
    witness: string
    rating: number
    email: string 
    state: AdminState
    createdAt: Date
    category: CategoryResponse
    tags: TagResponse[]
    media: MediaResponse
    image?: Image
}