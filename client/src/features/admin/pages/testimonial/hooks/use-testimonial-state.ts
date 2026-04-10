import { useState } from "react";
import type { Testimonial } from "../../../models/testimonial";
import advanceTestimonialService from "../../../services/testimonial/advance-testimonial.service";


const useTestimonialState = (testimonial: Testimonial) => {
    
    const [updateTestimonial, setUpdateTestimonial] = useState<Testimonial>(testimonial);

    const nextState = async (id: number) => setUpdateTestimonial(await advanceTestimonialService(id)); 
    const prevState = async (id: number) => setUpdateTestimonial(await advanceTestimonialService(id)); 

    return { updateTestimonial, nextState, prevState };
}

export default useTestimonialState;