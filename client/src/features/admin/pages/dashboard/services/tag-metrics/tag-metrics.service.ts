import api from "../../../../../../core/api/api";
import { GET_TAG_METRICS_API } from "../../../../../../core/api/urls/urls";
import type { TagMetrics } from "../../models/tag-metrics";

async function getTagMetrics(): Promise<TagMetrics[]> {
    const metrics = await api.get<TagMetrics[]>(GET_TAG_METRICS_API);
    return metrics.data;
}

export default getTagMetrics;