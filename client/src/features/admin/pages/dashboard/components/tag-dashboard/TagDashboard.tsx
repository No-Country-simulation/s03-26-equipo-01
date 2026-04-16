import { TabValues } from "../../../../components/tab-container/tab-values";
import MetricCard from "../metric-card/MetricCard";
import type { TagDashboardProps } from "./tag-dashboard";
import './styles/tag-dashboard.css';

const TagDashboard = ({currentTab, metrics}: TagDashboardProps) => {
    return (
        currentTab === TabValues.TAG && 
        <section className = 'tag-dashboard-container falling-container'>
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