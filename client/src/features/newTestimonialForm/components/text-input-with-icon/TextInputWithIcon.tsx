import "./styles/text-input-with-icon.css"
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import InputAdornment from "@mui/material/InputAdornment"
import TextField  from "@mui/material/TextField";

interface TextInputWithIconProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
  icon: React.ReactNode
}

export const TextInputWithIcon = <T extends FieldValues>({ name, control, label, placeholder, icon, rules } : TextInputWithIconProps<T>) => {
  const id = `input-${name}`;
  return(
    <div className="new-testimonial_input-with-icon-container">
      <label htmlFor={id}> {label} </label>
      <div className="new-testimonial_input-with-icon-action-group">
        <div className="new-testimonial_generic-icon-container">
          <InputAdornment position="start" sx={{marginRight: 0}}>{icon}</InputAdornment>
        </div>
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
              sx={{ fontSize:"var(--primary-font)"}}
            />
          )}
        />
      </div>
    </div>
)};