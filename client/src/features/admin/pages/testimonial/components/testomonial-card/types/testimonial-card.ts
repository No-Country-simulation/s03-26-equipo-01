import type { Testimonial } from "../../../../../models/testimonial";

export interface TestimonialCardProps {
    testimonial: Testimonial
}

export interface TestimonialCardContentProps {
    testimonial: Testimonial
    changeToDiscart: (id: number) => void
    changeToPublished: (id: number) => void
    changeToAproved: (id: number) => void
    changeToDraft: (id: number) => void
}