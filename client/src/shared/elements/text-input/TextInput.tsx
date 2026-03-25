import { TextField } from '@mui/material';
import './text-input.css';
import type { InputTextData } from '../../types/input-text-data';
import type { FieldValues, Path, UseFormRegister } from 'react-hook-form';

interface TextInputProps<T extends FieldValues> {
    inputTextData: InputTextData
    register: UseFormRegister<T>
}

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
            className = "text-input"
            sx={{
                '& .MuiInputBase-root': {
                    marginTop: '22px'
                },
                '& .MuiFormLabel-root': {
                    fontSize: '14px'
                }
            }}
        />
    )
}

export default TextInput;