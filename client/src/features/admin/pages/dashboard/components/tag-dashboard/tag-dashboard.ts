import type { TabValues } from "../../../../components/tab-container/tab-values"
import type { Metric } from "../../models/metric"

export interface TagDashboardProps {
    currentTab: TabValues
    metrics: Metric[]
}