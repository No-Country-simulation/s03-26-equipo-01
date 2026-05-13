import { useState } from "react";
import type { Testimonial } from "../../../models/testimonial";
import advanceTestimonialService from "../../../services/testimonial/advance-testimonial.service";
import deleteTestimonial from "../../../services/testimonial/delete-testimonial.service";
import useApi from "../../../../../core/api/hooks/use-api";
import type { ChangeStateResult } from "../components/testimonials-container/types/change-state-result";

const useTestimonialState = (testimonial: Testimonial) => {
    
    const [updateTestimonial, setUpdateTestimonial] = useState<Testimonial | null>(testimonial);
    const { patch, patchWithBody } = useApi<Testimonial>()

    const advance = async (id: number): Promise<ChangeStateResult> => {
        try {
            const changeTestimonial = await patchWithBody(advanceTestimonialService, id);
            setUpdateTestimonial(prev => !isDraft(changeTestimonial) ? changeTestimonial : prev);
            return {
                isError: false,
                state: changeTestimonial.state
            }
        }
        catch(error: unknown) {
            return {
                isError: !!error,
                state: updateTestimonial!.state
            }
        }
    } 
    
    const deleted = async (id: number): Promise<ChangeStateResult> => {
        try {
            await patch(deleteTestimonial, id);
            setUpdateTestimonial(null);
            return {
                isError: false,
                state: 'Eliminado'
            }
        }
        catch(error: unknown) {
            return {
                isError: !!error,
                state: updateTestimonial!.state
            }
        }
    }

    const isDraft = (changeTestimonial: Testimonial): boolean => changeTestimonial.state.toString() === 'Borrador' 

    return { updateTestimonial, advance, deleted };
}

export default useTestimonialState;