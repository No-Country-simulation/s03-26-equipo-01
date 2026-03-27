import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import { Box, FormControl, Input, InputAdornment, InputLabel} from "@mui/material";
import { alpha } from "@mui/material/styles";

interface InputWithIconProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
  icon: React.ReactNode
}

export const InputWithIcon = <T extends FieldValues>({ name, control, label, placeholder, icon } : InputWithIconProps<T>) => (
  <FormControl fullWidth variant="standard" sx={{display: "flex", flexDirection: "column", }}>
    <InputLabel shrink htmlFor={name} sx={{position:"static"}}>
      {label}
    </InputLabel>
    <Box sx={{ display: "flex", alignItems: "end", gap:"16px"}}>
      <Box sx={{backgroundColor: alpha("#2D2D2D", 0.16), borderRadius: "50%", width: "40px", height:"40px", flexShrink: 0 , display: "flex", alignItems: "center", justifyContent: "center", padding: "8px"}}>
        {<InputAdornment position="start" sx={{marginRight: 0}}>{icon}</InputAdornment>}
      </Box>
      <Controller
        name={name}
        control={control}
        render={({ field, fieldState: { error } }) => (
          <Input
            id={name}
            {...field}
            error={!!error}
            fullWidth
            placeholder={placeholder}
            sx={{height:"40px"}}
          />
        )}
      />
    </Box>
  </FormControl>
);