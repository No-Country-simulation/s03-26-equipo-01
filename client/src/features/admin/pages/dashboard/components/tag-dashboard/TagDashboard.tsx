import { TabValues } from "../../../../components/tab-container/tab-values";
import MetricCard from "../metric-card/MetricCard";
import type { TagDashboardProps } from "./tag-dashboard";
import './styles/tag-dashboard.css';

const TagDashboard = ({currentTab, metrics}: TagDashboardProps) => {
    return (
        <section hidden = {currentTab !== TabValues.TAG} className = 'tag-dashboard-container'>
                {metrics.map(metric =>
                    <MetricCard 
                        key = {metric.id} 
                        metric = {metric} 
                    />
                )}
            </section>
    )
}

export default TagDashboard;