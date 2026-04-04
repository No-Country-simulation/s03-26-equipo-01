import { FormControl, InputLabel, MenuItem, Select, type SelectChangeEvent } from "@mui/material";
import type { SelectInputData } from "../../types/select-input-data/select-input-data";
import './select-input.css'
import { useState } from "react";

interface SelectInputProps {
    selectInputData: SelectInputData
    handleSubmit: (value: string, type: string) => void
}

const SelectInput = ({selectInputData, handleSubmit}: SelectInputProps) => {

    const [content, setContent] = useState<string>('');
    const handleChange = (value: SelectChangeEvent) => {
        handleSubmit(value.target.value, selectInputData.type);
        setContent(value.target.value)
    }

    return (
        <FormControl fullWidth>
            <InputLabel className = 'select-input_label'>{selectInputData.placeholder}</InputLabel>
            <Select 
                className = 'select-input_container'
                id = {selectInputData.id.toString()}
                name = {selectInputData.name}
                value = {content}
                onChange = {handleChange}>
                    {selectInputData.content.map(content => 
                        <MenuItem value = {content}>{content}</MenuItem>
                    )}
            </Select>
        </FormControl>
    )
}

export default SelectInput;