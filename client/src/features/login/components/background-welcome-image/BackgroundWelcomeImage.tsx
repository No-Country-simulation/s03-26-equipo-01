import image from '../../../../assets/login-background.png'
import './styles/background-welcome-image.css';


const BackgroundWelcomeImage = () => {
    return (
        <figure className = 'background-welcome-image-container'>
            <img src={image} alt = 'Imagen de fondo para el formulario del login' />
        </figure>
    )
}

export default BackgroundWelcomeImage;