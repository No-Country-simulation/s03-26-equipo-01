import type { Tag } from "../../../models/tag"

export type TagMetrics = Tag & TagData

interface TagData {
    testimonialsCount: number
}