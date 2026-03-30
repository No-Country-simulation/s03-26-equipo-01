import type { CredentialsErrorContainerProps } from './credentials-error-container';
import './styles/credentials-error-container.css';
import image from '../../../../assets/icon.svg';

const CredentialsErrorContainer = ({message, close}: CredentialsErrorContainerProps) => {
    
    return (
        <section className = 'credentials-error-container'>
            <div className = 'credentials-error-container_red-container'></div>
            <div className = 'credentials-error-container_message'>
                <p>{message}</p>
            </div>
            <figcaption className = 'credentials-error-container_close'>
                <img 
                    src={image} 
                    alt = 'Icono para cerrar el mensaje de error' 
                    onClick = {() => close()} />
            </figcaption>
        </section>
    )
}

export default CredentialsErrorContainer;