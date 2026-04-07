import type { Image } from "../../../models/image"
import type { AdminState } from "../../../models/state"

export interface TestimonialResponse {
    id: number, 
    testimonial: string 
    idEmbed: number 
    rating: number
    email: string 
    state: AdminState
    createdAt: Date
    image: Image
}