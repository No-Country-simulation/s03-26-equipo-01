import './logo-container.css';
import logo from '../../../../../assets/Logo.svg';

const LogoContainer = () => {
    return (
        <div className = 'sidebar-logo-container'>
            <figure>
                <img src = {logo} alt='Logo principal de Voz Activa' />
            </figure>
        </div>
    )
}

export default LogoContainer;
