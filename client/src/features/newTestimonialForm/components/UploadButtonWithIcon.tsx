import { Box, Button, FormControl, InputAdornment, FormLabel} from "@mui/material"
import { alpha } from "@mui/material/styles";
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form";

interface UploadButtonWithIconProps<T extends FieldValues>{
  name: Path<T>,
  control:Control<T>
  label: string,
  icon: React.ReactNode
}

const UploadButtonWithIcon = <T extends FieldValues>({name, control, label, icon}:UploadButtonWithIconProps<T>) => {
  const id = `input-${name}`;
  return (
    <FormControl>
      <Box sx={{display:"flex", flexDirection:"column", gap:"12px", width:"275px"}}>
        <FormLabel htmlFor={id} sx={{position:"static", fontSize:"1.4rem"}}> {label} </FormLabel>
        <Box sx={{ display: "flex", alignItems: "center", gap:"16px"}}>
          <Box sx={{backgroundColor: alpha("#2D2D2D", 0.16), borderRadius: "50%", width: "40px", height:"40px", flexShrink: 0 , display: "flex", alignItems: "center", justifyContent: "center", padding: "8px"}}>
            {<InputAdornment position="start" sx={{marginRight: 0}}>{icon}</InputAdornment>}
          </Box>
          <Controller
            name={name}
            control={control}
            render={({ field }) => (
              <Button
                component="label"
                role={undefined}
                variant="contained"
                tabIndex={-1}
                color="primary"
                fullWidth
                sx={{width:"219px", height:"42px", fontSize:"1.5rem", fontWeight: "Medium"}}
              >
                SELECCIONAR ARCHIVO
                <input
                  {...field}
                  id={id}
                  type="file"
                  accept="image/jpeg, image/png, image/webp, image/gif"
                  hidden
                  onChange={(e) => {
                    const file = e.target.files?.[0];
                    console.log(file)
                  }}
                />
              </Button>
            )}
          />
        </Box>
      </Box>
    </FormControl>
  ) 
}

export default UploadButtonWithIcon


