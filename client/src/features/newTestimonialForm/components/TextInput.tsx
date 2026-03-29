import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import FormLabel from "@mui/material/FormLabel";
import Input  from "@mui/material/Input";
import Box  from "@mui/material/Box";

interface TextInputProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
}

export const TextInput = <T extends FieldValues>({ name, control, label, placeholder} : TextInputProps<T>) => {
  return(
    <Box sx={{display:"flex", flexDirection:"column", width:"100%", minWidth:"141px" ,maxWidth:"341px" ,gap:"6px"}}>
      <FormLabel sx={{position:"static", fontSize:"1.4rem"}}> {label} </FormLabel>
      <Controller
        name={name}
        control={control}
        render={({ field, fieldState: { error } }) => (
          <Input
            {...field}           
            error={!!error}
            fullWidth
            placeholder={placeholder}
            sx={{fontSize:"1.6rem"}}
          />
        )}
      />
  </Box>
)};