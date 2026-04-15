import './styles/combo-box.css'
import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
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
  onSearch?: (value: string) => void,
  searchValue?: string
}

const ComboBox = <T extends FieldValues>({name, control, label, rules, placeholder, data, loading = false, onSearch, searchValue = ''} : ComboBoxProps<T>) => {
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
            multiple
            id={id}
            inputValue={searchValue}
            value={data.filter((item) => Array.isArray(value) && value.includes(item.id))}
            onChange={(_, newValue) => {
              onChange(newValue.map((item) => item.id));
            }}
            onInputChange={(_, newInputValue, reason) => {
              if ((reason === 'input' || reason === 'clear') && onSearch) {
                onSearch(newInputValue);
              }
            }}
            getOptionLabel={(option) => option.label || ""}
            isOptionEqualToValue={(option, value) => option.id === value.id}
            filterSelectedOptions
            forcePopupIcon={false}
            disablePortal
            options={data}
            loading={loading}
            renderTags={(selected, getTagProps) =>
              selected.map((option, index) => (
                <Chip
                  {...getTagProps({ index })}
                  key={option.id}
                  label={option.label}
                  size="small"
                  avatar={
                    <Avatar sx={{ width: 24, height: 24, fontSize: 12 }}>
                      {option.label.charAt(0).toUpperCase()}
                    </Avatar>
                  }
                  sx={{
                    borderRadius: '999px',
                    backgroundColor: 'rgba(45,45,45,0.08)',
                  }}
                />
              ))
            }
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
