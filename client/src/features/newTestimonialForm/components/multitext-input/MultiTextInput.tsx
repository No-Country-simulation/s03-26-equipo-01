import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import { TextField } from "@mui/material";

interface MultitextInputProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  rows: number,
  placeholder?: string,
}

export const MultitextInput = <T extends FieldValues>({ name, control, label, placeholder, rows, rules} : MultitextInputProps<T>) => {
  const id = `input-${name}`;
  return(
    <div className ="new-testimonial_multitext-container">
      <label htmlFor={id}> {label} </label>
      <Controller
        name={name}
        control={control}
        rules={rules}
        render={({ field, fieldState: { error } }) => (
          <TextField
            {...field}
            id={id}      
            error={!!error}
            fullWidth
            placeholder={placeholder}
            multiline= {true}
            maxRows={rows}
            variant="standard"
            sx={{
              fontSize:"var(--primary-font)"
            }}
          />
        )}
      />
  </div>
)};