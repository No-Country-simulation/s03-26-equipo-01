import { useForm } from 'react-hook-form';
import useAuthContext from '../../../../shared/auth/context/use-auth';
import TextInput from '../../../../shared/elements/text-input/TextInput';
import SubmitButton from '../submit-button/SubmitButton';
import './styles/form-login.css';
import inputsData from './input-data';
import type { UserCredentials } from '../../../../shared/auth/models/user-credentials';

const LoginForm = () => {

    const {login} = useAuthContext();
    const {register, handleSubmit} = useForm<UserCredentials>();
    const handleLogin = (data: UserCredentials) => login(data);

    return (
        <form 
            onSubmit = {handleSubmit(handleLogin)}
            className = 'form-container'>
            {inputsData.map(inputData => 
                <TextInput
                    register = {register}
                    key = {inputData.id}
                    inputTextData = {inputData} 
                />
            )}
            <SubmitButton />
        </form>
    )
}

export default LoginForm;