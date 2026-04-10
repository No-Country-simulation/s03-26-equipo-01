import type { Category } from "../../../models/category";

export type CategoryMetrics = Category & CategoryData

interface CategoryData {
    testimonialsCount: number
}