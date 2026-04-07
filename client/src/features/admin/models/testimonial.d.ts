import type { Image } from "./image"
import type { AdminState } from "./state"

export interface Testimonial {
    id: number, 
    testimonial: string 
    idEmbed: number 
    rating: number
    email: string 
    state: AdminState
    createdAt: Date
    image?: Image
}
