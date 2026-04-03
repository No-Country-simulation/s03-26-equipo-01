import './styles/combo-box.css'
import TextField from "@mui/material/TextField";
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
    <div className="combo-box-container" >
      <label htmlFor={id}>{label}</label>
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
              sx: { fontSize: "var(--primary-font)" }
            }
          }}/>}
          />
        )}
      />
  </div>
  )
}

export default ComboBox