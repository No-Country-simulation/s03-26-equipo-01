import type { MetricCardProps } from "./metric-card"
import './styles/metric-card.css';

const MetricCard = ({metric}: MetricCardProps) => {
    return (
        <article className = 'metric-card-container'>
            <div className = 'metric-card_title'>
                <h4>{metric.name}</h4>
            </div >
            <div className = 'metric-card_testionial-data'>
                <p className = 'metric-card_testionial-data--title'>Testimonios</p>
                <p className = 'metric-card_testionial-data--count'>{metric.testimonialsCount}</p>
            </div>
        </article>
    )
}

export default MetricCard