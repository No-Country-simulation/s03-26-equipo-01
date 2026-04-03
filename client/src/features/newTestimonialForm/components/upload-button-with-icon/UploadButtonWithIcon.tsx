import "./styles/upload-button-with-icon.css"
import { Button, InputAdornment} from "@mui/material"
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";

interface UploadButtonWithIconProps<T extends FieldValues>{
  name: Path<T>,
  control:Control<T>
  label: string,
  icon: React.ReactNode
  rules?: object
}

const UploadButtonWithIcon = <T extends FieldValues>({name, control, label, icon, rules}:UploadButtonWithIconProps<T>) => {
  const id = `input-${name}`;
  return (
      <div className="new-testimonial_upload-container">
        <label htmlFor={id}>{label}</label>
        <div className="new-testimonial_upload-action-group">
          <div className="new-testimonial_upload-icon-container">
            <InputAdornment position="start" sx={{marginRight: 0}}>{icon}</InputAdornment>
          </div>
          <Controller
            name={name}
            control={control}
            rules={rules}
            render={({ field: { onChange, ...field } }) => (
              <Button
                component="label"
                role={undefined}
                variant="contained"
                tabIndex={-1}
                sx={{
                  width:"auto",
                  maxWidth:"219px", 
                  maxHeight:"42px",
                  padding:"0.4em 1.5em",
                  fontWeight: "Medium",
                  backgroundColor: "var(--secondary-color)",
                }}
              >
                <span className="new-testimonial-upload_text-button">SELECCIONAR ARCHIVO</span>
                <input
                  {...field}
                  id={id}
                  type="file"
                  accept="image/jpeg, image/png, image/webp, image/gif"
                  hidden
                  onChange={(e) => {
                    const file = e.target.files?.[0];
                    if (file) {
                      onChange(file); 
                    }
                  }}
                  value=""
                />
              </Button>
            )}
          />
        </div>
      </div>
  ) 
}

export default UploadButtonWithIcon


