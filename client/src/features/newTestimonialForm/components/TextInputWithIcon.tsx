import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import { Box, FormControl, Input, InputAdornment, FormLabel} from "@mui/material";
import { alpha } from "@mui/material/styles";

interface TextInputWithIconProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
  icon: React.ReactNode
}

export const TextInputWithIcon = <T extends FieldValues>({ name, control, label, placeholder, icon } : TextInputWithIconProps<T>) => {
  const id = `input-${name}`;
  return(
  <FormControl sx={{width:"100%", minWidth:"275px", maxWidth:"404px"}}>
    <Box sx={{display:"flex", flexDirection:"column", gap:"12px"}}>
      <FormLabel htmlFor={id} sx={{position:"static", fontSize:"1.4rem"}}> {label} </FormLabel>
      <Box sx={{ display: "flex", alignItems: "end", gap:"16px", width:"100%"}}>
        <Box sx={{backgroundColor: alpha("#2D2D2D", 0.16), borderRadius: "50%", width: "40px", height:"40px", flexShrink: 0 , display: "flex", alignItems: "center", justifyContent: "center", padding: "8px"}}>
          {<InputAdornment position="start" sx={{marginRight: 0}}>{icon}</InputAdornment>}
        </Box>
        <Controller
          name={name}
          control={control}
          render={({ field, fieldState: { error } }) => (
            <Input
              id={id}
              {...field}
              error={!!error}
              fullWidth
              placeholder={placeholder}
              sx={{height:"40px", fontSize:"1.6rem"}}
            />
          )}
        />
      </Box>
    </Box>
  </FormControl>
)};