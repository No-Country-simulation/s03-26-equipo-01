import { Button } from "@mui/material";


const SubmitButton = () => {
    return (
        <Button 
            type = 'submit'
            variant="contained"
            sx={{
                alignSelf: 'end',
                width: '100%',
                minHeight: '6vh',
                fontSize: 'var(--primary-font)',
                fontWeight: 'medium',
                backgroundColor: 'var(--primary-color)',
                color: 'var(--secondary-color)'
            }}>
                Iniciar Sesión
        </Button>
    )
}

export default SubmitButton;