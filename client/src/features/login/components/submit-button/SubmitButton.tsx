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
                backgroundColor: 'var(--accent-color)',
                color: '#FFF'
            }}>
                Iniciar Sesión
        </Button>
    )
}

export default SubmitButton;