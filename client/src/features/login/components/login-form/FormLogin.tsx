import TextInput from '../../../../shared/elements/text-input/TextInput';
import './form-login.css';
import inputsData from './input-data';

const LoginForm = () => {
    return (
        <form action="" className='form-container'>
            {inputsData.map(inputData => 
                <TextInput 
                    key = {inputData.id}
                    inputTextData = {inputData} />
            )}
        </form>
    )
}

export default LoginForm;