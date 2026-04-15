import { Button } from "@mui/material"
import { Send } from "lucide-react"

interface SubmitButtonProps {
  isAvailable: boolean,
}

const SubmitButton = ({isAvailable = false} : SubmitButtonProps) => {
  return (
    <Button
      type='submit'
      variant="contained" 
      endIcon={<Send size = {20}/>} 
      disabled = {!isAvailable}
      sx={{
        alignSelf: 'center',
        width:'100%',
        minHeight: '3.75rem',
        fontSize:'1.5rem',
        fontWeight:'medium',
        padding:'0.5rem 1.375rem', 
        backgroundColor: 'var(--secondary-color)',
        color: '#FFF'
      }}
      >
      Enviar
    </Button>
  )
}

export default SubmitButton
