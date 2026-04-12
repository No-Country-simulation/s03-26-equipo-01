import type { TabValues } from "../../../../components/tab-container/tab-values"
import type { TagMetrics } from "../../models/tag-metrics"

export interface TagDashboardProps {
    currentTab: TabValues
    metrics: TagMetrics[]
}