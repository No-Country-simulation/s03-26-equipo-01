import { Checkbox, FormControl, FormControlLabel, FormHelperText } from "@mui/material";
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form"

interface AuthorizationCheckboxProps<T extends FieldValues> {
  name: Path<T>,
  control: Control <T>,
  text: string
}

const AuthorizationCheckbox = <T extends FieldValues>({name, control, text} : AuthorizationCheckboxProps<T>) => (
  <Controller
    name={name}
    control={control}
    render={({ field, fieldState: { error } }) => (
      <FormControl>
        <FormControlLabel control={<Checkbox {...field} />} label={text} />
        {error && <FormHelperText>{error.message}</FormHelperText>}
      </FormControl>
    )}
  />
)

export default AuthorizationCheckbox;