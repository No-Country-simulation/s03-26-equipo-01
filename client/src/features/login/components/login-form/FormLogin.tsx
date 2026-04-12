import { useForm } from 'react-hook-form';
import useAuthContext from '../../../../shared/auth/context/use-auth-context';
import TextInput from '../../../../shared/elements/text-input/TextInput';
import SubmitButton from '../submit-button/SubmitButton';
import './styles/form-login.css';
import inputsData from './input-data';
import type { AuthCredentials } from '../../../../shared/auth/models/user-credentials';
import schema from './schema';
import { yupResolver } from '@hookform/resolvers/yup';

const LoginForm = () => {

    const {login} = useAuthContext();
    const {register, handleSubmit, formState: {errors}} = useForm<AuthCredentials>({
        resolver: yupResolver(schema),
        mode: 'onChange'
    });
    const handleLogin = (data: AuthCredentials) => login(data);

    return (
        <form 
            onSubmit = {handleSubmit(handleLogin)}
            className = 'form-container'>
            {inputsData.map(inputData => 
                <TextInput
                    register = {register}
                    error = {errors[inputData.name as keyof AuthCredentials]?.message}
                    key = {inputData.id}
                    inputTextData = {inputData} 
                />
            )}
            <SubmitButton />
        </form>
    )
}

export default LoginForm;