import type { FilterData } from "../../../../../../shared/types/filter-data/filter-data";
import type TestimonialResources from "../../../../models/testimonial-resources";

export interface FiltersContainerProps {
    adminResources: TestimonialResources
    onFilter: (filter: FilterData) => void
}