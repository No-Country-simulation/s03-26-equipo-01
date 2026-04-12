import { TabValues } from "../../../../components/tab-container/tab-values";
import MetricCard from "../metric-card/MetricCard";
import type { CategoryDashboardProps } from "./category-dashboard";
import './styles/category-dashboard.css';

const CategoryDashboard = ({currentTab, metrics}: CategoryDashboardProps) => {    
    return (
            <section hidden = {currentTab !== TabValues.CATEGORIA} className = 'category-dashboard-container'>
                {metrics.map(metric =>
                    <MetricCard 
                        key = {metric.id} 
                        metric = {metric} 
                        />
                )}
            </section>
    )
}

export default CategoryDashboard;