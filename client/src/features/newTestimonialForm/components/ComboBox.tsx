import TextField from "@mui/material/TextField";
import FormLabel from "@mui/material/FormLabel";
import Box from "@mui/material/Box";
import Autocomplete from "@mui/material/Autocomplete";
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";

interface DataProps {
    id: number,
    label: string
}

interface ComboBoxProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
  data: DataProps[]
}

const ComboBox = <T extends FieldValues>({name, control, label, rules, placeholder, data} : ComboBoxProps<T>) => {
  const id = `input-${name}`;
  return (
     <Box sx={{display:"flex", flexDirection:"column", width:"100%", minWidth:"141px" ,maxWidth:"341px" ,gap:"6px"}}>
      <FormLabel  htmlFor={id} color="secondary" sx={{position:"static", fontSize:"1.4rem"}}> {label} </FormLabel>
      <Controller
        name={name}
        control={control}
        rules={rules}
        render={({ field: { onChange, value } }) => (
          <Autocomplete
            id={id}
            value={data.find((item) => item.id === value) || null}
            onChange={(_, newValue) => {
              onChange(newValue ? newValue.id : null);
            }}
            getOptionLabel={(option) => option.label || ""}
            isOptionEqualToValue={(option, value) => option.id === value.id}
            forcePopupIcon={false}
            disablePortal
            options={data}
            renderInput={(params) => <TextField variant="standard" {...params} label="" placeholder={placeholder}  slotProps={{
            input: {
              ...params.InputProps, 
              sx: { fontSize: "1.6rem" }
            }
          }}/>}
          />
        )}
      />
  </Box>
  )
}

export default ComboBox