import api from "../../../../core/api/api";
import { TESTIMONIAL_ARCHIVED_API } from "../../../../core/api/urls/urls";

async function deleteTestimonial(id: number): Promise<void> {
    await api.patch(TESTIMONIAL_ARCHIVED_API(id));
}

export default deleteTestimonial;