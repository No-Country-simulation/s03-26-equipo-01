import api from "../../../../core/api/api";
import { ADMIN_TESTIMONIALS_API } from "../../../../core/api/urls/urls";
import type { TestimonialResponse } from "../../adapters/testimonial/dtos/response";
import adminTestimonialsAdapter from "../../adapters/testimonial/testimonial.adapter";
import type { Testimonial } from "../../models/testimonial";

async function adminTestimonials(): Promise<Testimonial[]> {
    const testimonials = await api.get<TestimonialResponse[]>(ADMIN_TESTIMONIALS_API);
    return adminTestimonialsAdapter(testimonials.data);
}

export default adminTestimonials;