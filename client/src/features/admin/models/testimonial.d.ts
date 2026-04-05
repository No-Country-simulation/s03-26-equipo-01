import type { State } from "./state"

export interface Testimonial {
    id: number, 
    testimonial: string 
    idEmbed: number 
    rating: number
    email: string 
    state: State
    createdAt: Date
}
