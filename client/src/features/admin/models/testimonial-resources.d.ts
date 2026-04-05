import type { Category } from "./category"
import type { Editor } from "./editor"

export interface TestimonialResources {
    users: Editor[]
    category: Category[]
    states: string[]
}

export default TestimonialResources