import { Button } from "@mui/material";
import './submit-button.css';

const SubmitButton = () => {
    return (
        <Button 
            type = 'submit'
            variant="contained"
            className = 'submit-button'
        >
                Iniciar Sesión
        </Button>
    )
}

export default SubmitButton;