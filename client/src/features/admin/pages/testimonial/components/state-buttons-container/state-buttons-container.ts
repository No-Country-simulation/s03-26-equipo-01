import type { SelectStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { Testimonial } from "../../../../models/testimonial"

export interface StateButtonContainerProps {
    changeStateButtons: SelectStateButtons
    testimonial: Testimonial
}