import type { MetricCardProps } from "./metric-card"
import './styles/metric-card.css';

const MetricCard = ({metric}: MetricCardProps) => {
    return (
        <article className = 'metric-card-container'>
            <div className = 'metric-card_title'>
                <p>{metric.name}</p>
            </div >
            <div className = 'metric-card_testionial-data'>
                <p className = 'metric-card_testionial-data--count'>{metric.testimonialsCount}</p>
                <p className = 'metric-card_testionial-data--title'>Testimonios</p>
            </div>
        </article>
    )
}

export default MetricCard