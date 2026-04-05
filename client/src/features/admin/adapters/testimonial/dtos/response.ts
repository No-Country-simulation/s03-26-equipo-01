import type { Image } from "../../../models/image"
import type { State } from "../../../models/state"

export interface TestimonialResponse {
    id: number, 
    testimonial: string 
    idEmbed: number 
    rating: number
    email: string 
    state: State
    createdAt: Date
    image: Image
}