import { TextField } from '@mui/material';
import './styles/text-input.css';
import type { TextInputProps } from './text-input-props';
import useFocus from './hooks/use-focus';


const TextInput = ({inputTextData}: TextInputProps) => {

    const {onFocus, changeFocus, handleCounter} = useFocus();

    return (
        <div className='text-input-container'>
            <label htmlFor={inputTextData.name}>{inputTextData.label}</label>
            <TextField 
                onFocus = {() => changeFocus()}
                onBlur = {() => changeFocus()}
                onChange = {() => handleCounter()}
                id = {inputTextData.id} 
                name = {inputTextData.name}
                required = {inputTextData.required}
                type = {inputTextData.type}
                label = {inputTextData.placeholder}
                variant = 'standard' 
                className = 'text-input'
                slotProps={{
                    inputLabel: {
                        className: onFocus ? 'text-input-placeholder--on-focus' : 'text-input_placeholder',
                    },
                    input: {
                        className: 'text-input_container'
                    }
                }}
            />
        </div>
    )
}

export default TextInput;