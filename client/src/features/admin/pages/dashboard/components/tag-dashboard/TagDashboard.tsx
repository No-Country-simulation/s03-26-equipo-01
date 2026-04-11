import { TabValues } from "../../../../components/tab-container/tab-values";
import type { TagMetrics } from "../../models/tag-metrics";

interface TagDashboardProps {
    currentTab: TabValues
    metrics: TagMetrics[]
}

const TagDashboard = ({currentTab, metrics}: TagDashboardProps) => {
    return (
        <section hidden = {currentTab !== TabValues.TAG}>
                {metrics.map(metric => <p key={metric.id}>{metric.name}</p>)}
            </section>
    )
}

export default TagDashboard;