import { FormControl, InputLabel, MenuItem, Select, type SelectChangeEvent } from "@mui/material";
import type { SelectInputData } from "../../types/select-input-data/select-input-data";
import './select-input.css'
import { useState } from "react";
import type { FilterData } from "../../types/filter-data/filter-data";

interface SelectInputProps {
    selectInputData: SelectInputData
    handleSubmit: (value: string, type: keyof FilterData) => void
}

const SelectInput = ({selectInputData, handleSubmit}: SelectInputProps) => {

    const [content, setContent] = useState<string>('');
    const handleChange = (value: SelectChangeEvent) => {
        handleSubmit(value.target.value, selectInputData.type);
        setContent(value.target.value)
    }

    return (
        <FormControl fullWidth>
            <InputLabel id = {selectInputData.id.toString()}>{selectInputData.placeholder}</InputLabel>
            <Select 
                label = {selectInputData.placeholder}
                labelId = {selectInputData.id.toString()}
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