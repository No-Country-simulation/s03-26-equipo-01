import type { Category } from "./category"
import type { Media } from "./media"
import type { AdminState } from "./state"
import type { Tag } from "./tag"

export interface Testimonial {
    id: number, 
    testimonial: string 
    witness: string
    rating: number
    state: AdminState
    createdAt: Date
    category: Category
    tags: Tag[]
    media: Media
}