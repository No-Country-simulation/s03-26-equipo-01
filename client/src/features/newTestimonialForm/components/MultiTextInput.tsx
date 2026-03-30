import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";
import FormLabel from "@mui/material/FormLabel";
import Input  from "@mui/material/Input";
import Box  from "@mui/material/Box";
import { alpha } from "@mui/material/styles";

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
    <Box sx={{display:"flex", flexDirection:"column", width:"100%" ,gap:"6px"}}>
      <FormLabel htmlFor={id} sx={{position:"static", fontSize:"1.4rem"}}> {label} </FormLabel>
      <Controller
        name={name}
        control={control}
        rules={rules}
        render={({ field, fieldState: { error } }) => (
          <Input
            {...field}
            id={id}      
            error={!!error}
            fullWidth
            placeholder={placeholder}
            multiline= {true}
            rows={rows}
            sx={{height:"152px",border: `1px solid ${alpha('#474747', 0.60)}`, borderRadius:"4px", padding:"16px 14px", fontSize:"1.6rem"}}
          />
        )}
      />
  </Box>
)};