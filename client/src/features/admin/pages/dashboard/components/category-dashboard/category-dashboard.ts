import type { TabValues } from "../../../../components/tab-container/tab-values"
import type { CategoryMetrics } from "../../models/categories-metrics"

export interface CategoryDashboardProps {
    currentTab: TabValues
    metrics: CategoryMetrics[]
}
