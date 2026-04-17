import "./styles/authorization-checkbox.css"
import { Checkbox, FormControl, Box, Typography} from "@mui/material";
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form"

interface AuthorizationCheckboxProps<T extends FieldValues> {
  name: Path<T>,
  control: Control <T>,
  text: string,
  rules?: object
}

const AuthorizationCheckbox = <T extends FieldValues>({name, control, text, rules} : AuthorizationCheckboxProps<T>) => {
  return(
  <div className="new-testimonial_checkbox-container">
    <Controller
      name={name}
      control={control}
      rules={rules}
      render={({ field }) => (
        <FormControl sx={{display:"flex", flexDirection:"row"  }}>
          <Box component="label" sx={{display:"flex", alignItems:"center"}}>
            <Checkbox
              checked={Boolean(field.value)}
              onChange={(_, checked) => field.onChange(checked)}
              onBlur={field.onBlur}
              inputRef={field.ref}
              sx={{ width:"2.625rem", height:"2.625rem" , flexShrink:"0"}}
              size="large"
            />
            <Typography
              variant="body1"
              sx={{
                fontSize: "var(--primary-font)",
                userSelect: "none",
                color:"var(--dark-text-color)",
                opacity:"1"
              }}
            >
              {text}
            </Typography>
          </Box>
        </FormControl>
      )}
    />
  </div>
)}

export default AuthorizationCheckbox;
