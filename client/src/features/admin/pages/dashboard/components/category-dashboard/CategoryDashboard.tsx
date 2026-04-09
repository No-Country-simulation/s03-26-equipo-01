import { TabValues } from "../../../../components/tab-container/tab-values";
import type { CategoryMetrics } from "../../models/categories-metrics";


interface CategoryDashboardProps {
    currentTab: TabValues
    metrics: CategoryMetrics[]
}

const CategoryDashboard = ({currentTab, metrics}: CategoryDashboardProps) => {
    
    return (
        <> 
            <p hidden = {currentTab !== TabValues.TAG}>categorias</p>
            {metrics.map(metric => {
                <div>
                    <p>{metric.name}</p>
                    <p>{metric.testimonialsCount}</p>
                </div>
            })}
        </>
    )
}

export default CategoryDashboard;