import api from "../../../../core/api/api";
import { TESTIMONIALS_ADVANCE_URL } from "../../../../core/api/urls/urls";
import type { TestimonialResponse } from "../../adapters/testimonial/dtos/response";
import { adminTestimonialAdapter } from "../../adapters/testimonial/testimonial.adapter";
import type { Testimonial } from "../../models/testimonial";

async function advanceTestimonialService(id: number): Promise<Testimonial> {
    const advancedTestimonial = await api.patch<TestimonialResponse>(TESTIMONIALS_ADVANCE_URL(id));
    return adminTestimonialAdapter(advancedTestimonial.data);
}

export default advanceTestimonialService;