import type { Testimonial } from "../../../../../models/testimonial";
import type { ChangeStateResult } from "../../testimonials-container/types/change-state-result";
import type { SelectState } from "../../testimonials-container/types/select-state";

export interface TestimonialCardProps {
    testimonial: Testimonial
    onChangeState: (result: ChangeStateResult) => void
}

export interface TestimonialCardContentProps {
    testimonial: Testimonial
    selectTo: (id: number, state: SelectState) => void
}