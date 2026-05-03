import type { Testimonial } from "../../../../../models/testimonial";
import type { SelectState } from "./select-state";

export interface TestimonialCardProps {
    testimonial: Testimonial
}

export interface TestimonialCardContentProps {
    testimonial: Testimonial
    selectTo: (id: number, state: SelectState) => void
}