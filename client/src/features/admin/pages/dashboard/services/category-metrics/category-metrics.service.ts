import api from "../../../../../../core/api/api";
import { GET_CATEGORY_METRICS_API } from "../../../../../../core/api/urls/urls";
import type { CategoryMetrics } from "../../models/categories-metrics";

async function getCategoryMetrics(): Promise<CategoryMetrics[]> {
    const metrics = await api.get<CategoryMetrics[]>(GET_CATEGORY_METRICS_API);
    return metrics.data;
}

export default getCategoryMetrics;