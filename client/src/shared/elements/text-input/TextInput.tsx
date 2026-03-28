import { TextField } from '@mui/material';
import './styles/text-input.css';
import type { TextInputProps } from './text-input-props';
import useFocus from './hooks/use-focus';
import type { FieldValues, Path } from 'react-hook-form';

const TextInput = <T extends FieldValues>({inputTextData, error, register}: TextInputProps<T>) => {

    const {onFocus, changeFocus, handleCounter} = useFocus();

    return (
        <div className='text-input-container'>
            <label htmlFor={inputTextData.name}>{inputTextData.label}</label>
            <TextField 
                {...register(inputTextData.name as Path<T>, 
                    {
                        onChange: () => handleCounter(),
                        onBlur: () => changeFocus()
                    }
                )}
                error = {error !== undefined}
                onFocus = {() => changeFocus()}
                id = {inputTextData.id} 
                name = {inputTextData.name}
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
            {error && <p className='text-input-error'>{error}</p>}
        </div>
    )
}

export default TextInput;