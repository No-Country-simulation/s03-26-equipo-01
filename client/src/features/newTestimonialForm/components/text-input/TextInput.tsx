import "./styles/text-input.css"
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";

import { TextField } from "@mui/material";

interface TextInputProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
}

export const TextInput = <T extends FieldValues>({ name, control, label, placeholder, rules} : TextInputProps<T>) => {
  const id = `input-${name}`;
  return(
    <div className = "new-testimonial_input-container">
      <label htmlFor={id}>{label}</label>
      <Controller
        name={name}
        control={control}
        rules={rules}
        render={({ field, fieldState: { error } }) => (
          <TextField {...field}
            id={id}
            variant="standard"
            error={!!error}
            fullWidth
            placeholder={placeholder}
            label= {null}
            sx={{fontSize:"var(--primary-font)"}}
          />
          
        )}
        
      />
  </div>
)};