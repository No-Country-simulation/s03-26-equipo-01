import { Controller, type Control, type FieldValues, type Path } from "react-hook-form"
import Radio from "@mui/material/Radio"
import RadioGroup from "@mui/material/RadioGroup"
import FormControl from "@mui/material/FormControl"
import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography"
import FormLabel from "@mui/material/FormLabel"

interface RatingPickerProps<T extends FieldValues>{
  name: Path<T>,
  control:Control<T>
  label: string,
}

const RatingPicker = <T extends FieldValues>({name, control, label} : RatingPickerProps<T>) => {
  const numbers = [1,2,3,4,5,6,7,8,9,10];

  return (
    <FormControl sx={{width:"100%", maxWidth:"340px"}}>
      <Box sx={{display:"flex", flexDirection:"column"}}>
        <FormLabel sx={{position:"static" , fontSize:"1.4rem"}}> {label} </FormLabel>
        <Controller
          name={name}
          control={control}
          render={({ field: {onChange, value, ref} }) => {
            const currentPickedNumber = Number(value) || 0;
            return (
            <RadioGroup row sx={{gap:"12px", padding:"8px 0", flexWrap: "nowrap"}} onChange={(e) => {
              onChange(Number(e.target.value))
            }}>
              {
                numbers.map((number, index)=>{
                  const isSelected = number <= currentPickedNumber;
                  return(
                    <Box  component="label" sx={{border:"1px solid #343C45", borderRadius:"4px", width:"24px", height:"24px", display:"flex", alignItems:"center", justifyContent:"center", position:"relative", cursor:"pointer", backgroundColor: isSelected ? '#465D8E' : 'transparent', flexShrink: 0}} key={index}>
                      <Radio
                        value={number}
                        sx={{ display: "none" }}
                        slotProps={{
                          input: { ref: ref }
                        }}
                      />
                      <Typography variant="body1" sx={{color:isSelected ? '#FFFFFF' : '#343C45' , userSelect: "none"}}> {number} </Typography>
                    </Box>        
                )})
              }
            </RadioGroup>
            )
          }} 
        />
      </Box>
    </FormControl>
  )
}

export default RatingPicker