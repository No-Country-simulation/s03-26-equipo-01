import api from "../../../../core/api/api";
import { TESTIMONIAL_RESOURCES_API } from "../../../../core/api/urls/urls";
import type { TestimonialResourcesResponse } from "../../adapters/testimonial-resources/dtos/response";
import testimonialResourcesAdapter from "../../adapters/testimonial-resources/testimonial-resources.adapter";
import type TestimonialResources from "../../models/testimonial-resources";


async function adminTestimonialResource(): Promise<TestimonialResources> {
    const testimonialResources = await api.get<TestimonialResourcesResponse>(TESTIMONIAL_RESOURCES_API);
    return testimonialResourcesAdapter(testimonialResources.data);
}

export default adminTestimonialResource;