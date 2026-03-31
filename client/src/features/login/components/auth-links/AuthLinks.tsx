import { Link } from 'react-router-dom';
import './auth-links.css';

const AuthLinks = () => {
    return (
        <div className='auth-links'>
            <section className='auth-links_password-links-container'>
                <p>¿Olvidaste tu contraseña?</p>
                <Link to="">Recuperar contraseña.</Link>
            </section>
            <section className='auth-links_helper-container'>
                <p>¿Necesitas ayuda?</p>
                <Link to="">Contactar a soporte.</Link>
            </section>
        </div>
    )
}

export default AuthLinks;