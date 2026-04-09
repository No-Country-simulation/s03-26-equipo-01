import { TabValues } from "../../../../components/tab-container/tab-values";
import type { TagMetrics } from "../../models/tag-metrics";

interface TagDashboardProps {
    currentTab: TabValues
    metrics: TagMetrics[]
}

const TagDashboard = ({currentTab, metrics}: TagDashboardProps) => {
    return (
        <> 
            <p hidden = {currentTab !== TabValues.TAG}>tags</p>
            {metrics.map(metric => {
                <div>
                    <p>{metric.name}</p>
                    <p>{metric.testimonialsCount}</p>
                </div>
            })}
        </>
    )
}

export default TagDashboard;