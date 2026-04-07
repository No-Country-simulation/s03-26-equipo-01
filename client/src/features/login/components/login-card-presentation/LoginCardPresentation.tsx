import useAuthContext from "../../../../shared/auth/context/use-auth-context";
import CredentialsErrorContainer from "../credentials-error-container/CredentialsErrorContainer";
import LoginFormContainer from "../login-form-container/LoginFormContainer"
import './login-card-presentation.css';

const LoginCardPresentation = () => {

    const {error, closeError} = useAuthContext();

    return (
        <div className='login-container'>
            <LoginFormContainer />
            {error && <CredentialsErrorContainer 
                message = {error.message} 
                close = {closeError }
            />}
        </div>
    )
}

export default LoginCardPresentation;