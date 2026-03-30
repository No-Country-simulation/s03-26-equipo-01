import { Checkbox, FormControl, FormHelperText, Box, Typography} from "@mui/material";
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
        <Box component="label" sx={{display:"flex", alignItems:"center"}}>
          <Checkbox {...field} sx={{ width:"42px", height:"42px" , flexShrink:"0"}} size="large"/>
          <Typography
            variant="body1"
            color="secondary"
            sx={{
              fontSize: "1.6rem",
              lineHeight: 1.4,
              userSelect: "none",
            }}
          >
            {text}
          </Typography>
        </Box>


        {error && <FormHelperText>{error.message}</FormHelperText>}
      </FormControl>
    )}
  />
)

export default AuthorizationCheckbox;