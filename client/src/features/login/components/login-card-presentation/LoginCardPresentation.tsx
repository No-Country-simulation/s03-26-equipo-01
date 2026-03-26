import useAuthContext from "../../../../shared/auth/context/use-auth";
import CredentialsErrorContainer from "../credentials-error-container/CredentialsErrorContainer";
import LoginFormContainer from "../login-form-container/LoginFormContainer"
import LogoContainer from "../logo-container/LogoContainer"
import './login-card-presentation.css';

const LoginCardPresentation = () => {

    const {error} = useAuthContext();

    return (
        <div className='login-container'>
            <LogoContainer />
            <LoginFormContainer />
            {error && <CredentialsErrorContainer message = {error.message}/>}
        </div>
    )
}

export default LoginCardPresentation;