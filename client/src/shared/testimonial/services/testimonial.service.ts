import api from "../../../core/api/api";
import { ADMIN_TESTIMONIALS_API } from "../../../core/api/urls/urls";
import getToken from "../../../core/services/token/get-token";
import type { Testimonial } from "../models/testimonial";

async function createTestimonial(data: Testimonial) {
    if (data.course === null) throw new Error("El curso debe ser un entero");
    if((data.rating === null)) throw new Error("La valoración debe ser un entero");

    try {
        const token = getToken();
     
        const formData = new FormData();
        //formData.append("fullName", data.fullName);
        formData.append("email", data.email);
        formData.append("testimonial", data.testimonial);
        //formData.append("course", data.course.toString());
        //formData.append("authorization", data.authorization.toString());
        formData.append("rating", data.rating.toString());
        //if (data.youtubeUrl) formData.append("youtubeUrl", data.youtubeUrl);
        if (data.image) formData.append("image", data.image);
        formData.append("idEmbed", "1");
        
        
        const response = await api.post(ADMIN_TESTIMONIALS_API, formData, {
            headers: {
                'Authorization': `Bearer ${token}`
            },
        });
        return response.data;
    } catch (error) {
        console.error("Error en la creación de testimonio:", error);
        throw error; 
    }
}

export default createTestimonial;