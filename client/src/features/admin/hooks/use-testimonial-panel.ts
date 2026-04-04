import { useEffect, useState } from "react"
import useApi from "../../../core/api/hooks/use-api";
import adminTestimonialResource from "../services/admin-testimonial-resource.service";
import type TestimonialResources from "../models/testimonial-resources";
import type { FilterData } from "../../../shared/types/filter-data/filter-data";

const useTestimonialPanel = () => {

    const [adminResources, setTestimonialPanel] = useState<TestimonialResources>();
    const {get} = useApi<TestimonialResources>();

    useEffect(() => {
        get(adminTestimonialResource)
            .then(adminResources => setTestimonialPanel(adminResources))
            .catch(error => console.error(error))
    }, [])

    const sendFilter = (filter: FilterData) => console.log(filter); 

    return {sendFilter, adminResources}
}

export default useTestimonialPanel;