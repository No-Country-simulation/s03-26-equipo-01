import { useState } from "react";
import type { Testimonial } from "../../../models/testimonial";
import advanceTestimonialService from "../../../services/testimonial/advance-testimonial.service";
import deleteTestimonial from "../../../services/testimonial/delete-testimonial.service";
import useApi from "../../../../../core/api/hooks/use-api";

const useTestimonialState = (testimonial: Testimonial) => {
    
    const [updateTestimonial, setUpdateTestimonial] = useState<Testimonial | null>(testimonial);
    const { patch, patchWithBody } = useApi<Testimonial>()

    const advance = async (id: number) => {
        const changeTestimonial = await patchWithBody(advanceTestimonialService, id);
        setUpdateTestimonial(!isDraft(changeTestimonial) ? changeTestimonial : null);
    } 
    
    const deleted = async (id: number) => {
        await patch(deleteTestimonial, id);
        setUpdateTestimonial(null);
    }

    const isDraft = (changeTestimonial: Testimonial): boolean => changeTestimonial.state.toString() === 'Borrador' 

    return { updateTestimonial, advance, deleted };
}

export default useTestimonialState;