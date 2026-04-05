import type { Image } from "../../../models/image"
import type { StateResponse } from "../../state/dtos/response"

export interface TestimonialResponse {
    id: number, 
    testimonial: string 
    idEmbed: number 
    rating: number
    email: string 
    state: StateResponse
    createdAt: Date
    image: Image
}