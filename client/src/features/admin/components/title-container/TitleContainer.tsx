import './styles/title-container.css';
import type { TitleContainerProps } from './title-container';

const TitleContainer = ({title, text}: TitleContainerProps) => {
    return (
        <header className = 'testimonial-admin-title-container falling-container'>
            <h2>{title}</h2>
            <p>{text}</p>
        </header>
    )
}

export default TitleContainer;