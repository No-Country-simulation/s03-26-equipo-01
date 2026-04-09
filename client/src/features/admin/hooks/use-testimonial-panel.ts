import { useEffect, useState } from "react"
import useApi from "../../../core/api/hooks/use-api";
import adminTestimonialResource from "../services/testimonial-resource/admin-testimonial-resource.service";
import type TestimonialResources from "../models/testimonial-resources";
import type { FilterData } from "../../../shared/types/filter-data/filter-data";
import type { Testimonial } from "../models/testimonial";
import adminTestimonials from "../services/testimonial/admin-testimonials";

const useTestimonialPanel = () => {

    const [adminResources, setAdminResources] = useState<TestimonialResources>();
    const [testimonials, setTestimonials] = useState<Testimonial[]>();
    const {get} = useApi<TestimonialResources>();

    const getters = () => [
        get(adminTestimonials)
            .then(testimonials => setTestimonials(testimonials))
            .catch(error => console.error(error)),
        get(adminTestimonialResource)
            .then(adminResources => setAdminResources(adminResources))
            .catch(error => console.error(error))
    ]

    useEffect(() => {
        getters()
    }, [])

    const sendFilter = (filter: FilterData) => console.log(filter); 

    return {sendFilter, adminResources, testimonials}
}

export default useTestimonialPanel;