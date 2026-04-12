import BackgroundWelcomeImage from '../components/background-welcome-image/BackgroundWelcomeImage';
import LoginCardPresentation from '../components/login-card-presentation/LoginCardPresentation';
import './login.css';

const Login = () => {
    return (
        <section className='login-page'>
            <BackgroundWelcomeImage />
            <LoginCardPresentation />
        </section>
    )
}

export default Login;