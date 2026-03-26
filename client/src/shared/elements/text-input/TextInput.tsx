import { TextField } from '@mui/material';
import './styles/text-input.css';
import type { FieldValues, Path } from 'react-hook-form';
import type { TextInputProps } from './text-input-props';
import { useState } from 'react';


const TextInput = <T extends FieldValues>({inputTextData, register}: TextInputProps<T>) => {

    const [onFocus, setOnfocus] = useState<boolean>(false);
    const [contentCount, setContentCount] = useState<number>(0);

    return (
        <div className='text-input-container'>
            <label htmlFor={inputTextData.name}>{inputTextData.label}</label>
            <TextField 
                {...register(inputTextData.name as Path<T>)}
                onFocus = {() => contentCount === 0 && setOnfocus(!onFocus)}
                onBlur = {() => contentCount === 0 && setOnfocus(!onFocus)}
                onChange = {() => setContentCount(contentCount + 1)}
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