import type { Category } from "./category"
import type { Editor } from "./editor"
import type { Tag } from "./tag"

export interface TestimonialResources {
    users: Editor[]
    category: Category[]
    states: string[]
    tags: Tag[]
}

export default TestimonialResources