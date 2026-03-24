import { Button } from "@mui/material";


const SubmitButton = () => {
    return (
        <Button 
            variant="contained"
            sx={{
                alignSelf: 'end',
                width: '100%',
                fontSize: '12px',
                fontWeight: 'bold',
                backgroundColor: 'var(--primary-color)',
                color: 'var(--secondary-color)'
            }}>
                Iniciar Sesión
        </Button>
    )
}

export default SubmitButton;