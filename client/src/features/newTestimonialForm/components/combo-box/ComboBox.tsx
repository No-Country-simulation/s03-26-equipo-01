import './styles/combo-box.css'
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";

export interface DataProps {
  id: number,
  label: string
}

interface ComboBoxProps <T extends FieldValues>{
  name: Path<T>,
  control: Control <T>,
  label: string,
  rules?: object,
  placeholder?: string,
  data: DataProps[],
  loading?: boolean,
  onSearch?: (value: string) => void
}

const ComboBox = <T extends FieldValues>({name, control, label, rules, placeholder, data, loading = false, onSearch} : ComboBoxProps<T>) => {
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
            onInputChange={(_, newInputValue, reason) => {
              if (reason === 'input' && onSearch) onSearch(newInputValue);
            }}
            getOptionLabel={(option) => option.label || ""}
            isOptionEqualToValue={(option, value) => option.id === value.id}
            forcePopupIcon={false}
            disablePortal
            options={data}
            loading={loading}
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
