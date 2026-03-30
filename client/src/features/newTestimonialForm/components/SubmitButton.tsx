import { Button } from "@mui/material"
import { Send } from "lucide-react"

interface SubmitButtonProps {
  isAvailable: boolean,
}

const SubmitButton = ({isAvailable = false} : SubmitButtonProps) => {
  return (
    <Button variant="contained" endIcon={<Send size = {22}/>} fullWidth color="primary" sx={{padding:'8px 22px', fontSize:"1.5rem"}} disabled = {isAvailable}>Enviar</Button>
  )
}

export default SubmitButton