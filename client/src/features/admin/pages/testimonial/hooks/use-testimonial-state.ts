import { useState } from "react";
import type { Testimonial } from "../../../models/testimonial";
import advanceTestimonialService from "../../../services/testimonial/advance-testimonial.service";
import deleteTestimonial from "../../../services/testimonial/delete-testimonial.service";

const useTestimonialState = (testimonial: Testimonial) => {
    
    const [updateTestimonial, setUpdateTestimonial] = useState<Testimonial | null>(testimonial);

    const advance = async (id: number) => {
        const changeTestimonial = await advanceTestimonialService(id);
        setUpdateTestimonial(!isDraft(changeTestimonial) ? changeTestimonial : null);
    } 
    
    const deleted = async (id: number) => {
        await deleteTestimonial(id);
        setUpdateTestimonial(null);
    }

    const isDraft = (changeTestimonial: Testimonial): boolean => changeTestimonial.state.toString() === 'Borrador' 

    return { updateTestimonial, advance, deleted };
}

export default useTestimonialState;