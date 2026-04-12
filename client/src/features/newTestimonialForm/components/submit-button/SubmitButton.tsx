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
      endIcon={<Send size = {22}/>} 
      disabled = {!isAvailable}
      sx={{
        alignSelf: 'center',
        width:'100%',
        minHeight: '6vh',
        fontSize:'1.5rem',
        fontWeight:'medium',
        padding:'8px 22px', 
        backgroundColor: 'var(--secondary-color)',
        color: '#FFF'
      }}
      >
      Enviar
    </Button>
  )
}

export default SubmitButton