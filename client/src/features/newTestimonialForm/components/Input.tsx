import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import { TextField } from "@mui/material";

interface InputProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  isMultiline?: boolean,
  rows?: number,
  placeholder?: string
}

export const Input = <T extends FieldValues>({ name, control, label, placeholder, isMultiline = false, rows } : InputProps<T>) => (
  <Controller
    name={name}
    control={control}
    render={({ field, fieldState: { error } }) => (
      <TextField
        {...field}
        label={label}
        variant="standard"
        error={!!error}
        helperText={error ? error.message : null}
        fullWidth
        placeholder={placeholder}
        multiline={isMultiline}
        rows={rows}
        slotProps={{ inputLabel: { shrink: true } }}
      />
    )}
  />
);