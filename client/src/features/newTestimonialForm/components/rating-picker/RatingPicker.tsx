import "./styles/rating-picker.css"
import { Controller, type Control, type FieldValues, type Path } from "react-hook-form"
import Radio from "@mui/material/Radio"
import RadioGroup from "@mui/material/RadioGroup"

import Typography from "@mui/material/Typography"

interface RatingPickerProps<T extends FieldValues>{
  name: Path<T>,
  control:Control<T>
  label: string,
  rules?: object
}

const RatingPicker = <T extends FieldValues>({name, control, label, rules} : RatingPickerProps<T>) => {
  const numbers = [1,2,3,4,5,6,7,8,9,10];
  const id = `input-${name}`;

  return (
      <div className="new-testimonial-rating_container">
        <label htmlFor={id}> {label} </label>
        <Controller
          name={name}
          control={control}
          rules={rules}
          render={({ field: {onChange, value, ref} }) => {
            const currentPickedNumber = Number(value) || 0;
            return (
            <RadioGroup id={id} row sx={{gap:"0.8em", padding:"8px 0", flexWrap: "nowrap"}} onChange={(e) => {
              onChange(Number(e.target.value))
            }}>
              {
                numbers.map((number, index)=>{
                  const isSelected = number <= currentPickedNumber;
                  return(
                    <label htmlFor={'option-'+index} key={index} className={`new-testimonial-rating_option-box ${isSelected && 'option-box-selected'}`}>
                      <Radio
                        id={'option-'+index}
                        value={number}
                        sx={{ display: "none" }}
                        slotProps={{
                          input: { ref: ref }
                        }}
                      />
                      <Typography variant="body1" sx={{color:isSelected ? '#FFFFFF' : '#343C45' , userSelect: "none"}}> {number} </Typography>
                    </label>        
                )})
              }
            </RadioGroup>
            )
          }} 
        />
      </div>
  )
}

export default RatingPicker