import { TextField } from '@mui/material';
import './text-input.css';
import type { InputTextData } from '../../types/input-text-data';

interface TextInputProps {
    inputTextData: InputTextData
}

const TextInput = ({inputTextData}: TextInputProps) => {
    return (
        <TextField 
            id={inputTextData.id} 
            name={inputTextData.name}
            required={inputTextData.required}
            type={inputTextData.type}
            label={inputTextData.label}
            variant='standard' 
            className="login-input"
        />
    )
}

export default TextInput;