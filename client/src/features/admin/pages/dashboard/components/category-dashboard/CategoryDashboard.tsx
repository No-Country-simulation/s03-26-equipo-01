import { TabValues } from "../../../../components/tab-container/tab-values";
import type { CategoryMetrics } from "../../models/categories-metrics";


interface CategoryDashboardProps {
    currentTab: TabValues
    metrics: CategoryMetrics[]
}

const CategoryDashboard = ({currentTab, metrics}: CategoryDashboardProps) => {    
    return (
            <section hidden = {currentTab !== TabValues.TAG}>
                {metrics.map(metric => <p key={metric.id}>{metric.name}</p>)}
            </section>
    )
}

export default CategoryDashboard;