import type { TabValues } from "../../../../components/tab-container/tab-values"
import type { Metric } from "../../models/metric"

export interface CategoryDashboardProps {
    currentTab: TabValues
    metrics: Metric[]
}
