import type { Testimonial } from "../../models/testimonial";
import type { TestimonialResponse } from "./dtos/response";

function adminTestimonialsAdapter(response: TestimonialResponse[]): Testimonial[] {
    return response.map(testimonial => adminTestimonialAdapter(testimonial));
}

function adminTestimonialAdapter(testimonial: TestimonialResponse): Testimonial {
    return {
        id: testimonial.id, 
        testimonial: testimonial.testimonial,
        idEmbed: testimonial.idEmbed,
        rating: testimonial.rating,
        email: testimonial.email, 
        state: testimonial.state,
        createdAt: testimonial.createdAt
    }
}

export default adminTestimonialsAdapter;