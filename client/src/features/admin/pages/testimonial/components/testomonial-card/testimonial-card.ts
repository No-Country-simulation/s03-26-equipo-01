import type { Testimonial } from "../../../../models/testimonial";

export interface TestimonialCardProps {
    testimonial: Testimonial
}

export interface TestimonialCardContentProps {
    testimonial: Testimonial
    advance: (id: number) => void 
    deleted: (id: number) => void
}