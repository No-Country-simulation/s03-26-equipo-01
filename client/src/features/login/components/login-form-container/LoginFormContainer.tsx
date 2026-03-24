import AuthLinks from '../auth-links/AuthLinks';
import FormTitle from '../form-title/FormTitle';
import LoginForm from '../login-form/FormLogin';
import './login-form-container.css';

const LoginFormContainer = () => {
    return (
        <section className='login-form'>
            <div className='login-form-container'>
                <FormTitle />
                <LoginForm />
                <AuthLinks />
            </div>
        </section>
    )
}

export default LoginFormContainer;