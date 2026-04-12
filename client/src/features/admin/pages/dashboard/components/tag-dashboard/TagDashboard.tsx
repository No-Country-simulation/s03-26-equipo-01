import { TabValues } from "../../../../components/tab-container/tab-values";
import type { TagDashboardProps } from "./tag-dashboard";

const TagDashboard = ({currentTab, metrics}: TagDashboardProps) => {
    return (
        <section hidden = {currentTab !== TabValues.TAG}>
                {metrics.map(metric => <p key={metric.id}>{metric.name}</p>)}
            </section>
    )
}

export default TagDashboard;