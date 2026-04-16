import { TabValues } from "../../../../components/tab-container/tab-values";
import MetricCard from "../metric-card/MetricCard";
import type { CategoryDashboardProps } from "./category-dashboard";
import './styles/category-dashboard.css';

const CategoryDashboard = ({currentTab, metrics}: CategoryDashboardProps) => {    
    return (
            currentTab === TabValues.CATEGORIA && 
            <section className = 'category-dashboard-container falling-container'>
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