import { TabValues } from "../../../../components/tab-container/tab-values";
import type { CategoryDashboardProps } from "./category-dashboard";

const CategoryDashboard = ({currentTab, metrics}: CategoryDashboardProps) => {    
    return (
            <section hidden = {currentTab !== TabValues.CATEGORIA}>
                {metrics.map(metric => <p key={metric.id}>{metric.name}</p>)}
            </section>
    )
}

export default CategoryDashboard;