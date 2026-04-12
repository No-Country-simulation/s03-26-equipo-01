import type { MetricCardProps } from "./metric-card"

const MetricCard = ({metric}: MetricCardProps) => {
    return (
        <article className = 'metric-card-container'>
            <div className = 'metric-card_title'>
                <h4>{metric.name}</h4>
            </div >
            <div className = 'metric-card_testionial-data'>
                <p>Testimonios</p>
                <p className = 'metric-card_testionial-data--count'>{metric.testimonialsCount}</p>
            </div>
        </article>
    )
}

export default MetricCard