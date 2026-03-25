import BackgroundWelcomeImage from '../components/background-welcome-image/BackgroundWelcomeImage';
import LoginFormContainer from '../components/login-form-container/LoginFormContainer';
import LogoContainer from '../components/logo-container/LogoContainer';
import './login.css';

const Login = () => {
    return (
        <section className='login-page'>
            <BackgroundWelcomeImage />
            <div className='login-container'>
                <LogoContainer />
                <LoginFormContainer />
            </div>
        </section>
    )
}

export default Login;