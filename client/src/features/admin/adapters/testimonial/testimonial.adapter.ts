import type { Testimonial } from "../../models/testimonial";
import { categoryAdapter } from "../category/category.adapter";
import { mediaAdapter } from "../media/media.adapter";
import { tagsAdapter } from "../tag/tag.adapter";
import type { TestimonialResponse } from "./dtos/response";

function adminTestimonialsAdapter(response: TestimonialResponse[]): Testimonial[] {
    return response.map(testimonial => adminTestimonialAdapter(testimonial));
}

export function adminTestimonialAdapter(testimonial: TestimonialResponse): Testimonial {
    return {
        id: testimonial.id, 
        testimonial: testimonial.testimonial,
        rating: testimonial.rating,
        witness: testimonial.witness,
        state: testimonial.state,
        createdAt: testimonial.createdAt,
        media: testimonial.media && mediaAdapter(testimonial.media),
        category: categoryAdapter(testimonial.category),
        tags: tagsAdapter(testimonial.tags),
    }
}

export default adminTestimonialsAdapter;