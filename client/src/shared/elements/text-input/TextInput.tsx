import { TextField } from '@mui/material';
import './styles/text-input.css';
import type { FieldValues, Path } from 'react-hook-form';
import type { TextInputProps } from './text-input-props';


const TextInput = <T extends FieldValues>({inputTextData, register}: TextInputProps<T>) => {
    return (
        <TextField 
            {...register(inputTextData.name as Path<T>)}
            id = {inputTextData.id} 
            name = {inputTextData.name}
            required = {inputTextData.required}
            type = {inputTextData.type}
            label = {inputTextData.label}
            variant = 'standard' 
            className = 'text-input'
            slotProps={{
                inputLabel: {
                    className: 'text-input_label'
                },
                input: {
                    className: 'text-input_container'
                }
            }}
        />
    )
}

export default TextInput;