import { Button } from "@mui/material"
import { alpha, createTheme, ThemeProvider } from "@mui/material/styles";
import { Send } from "lucide-react"

const theme = createTheme({
  palette: {
    primary: {
      main: '#1B2E45',
      // light: will be calculated from palette.primary.main,
      // dark: will be calculated from palette.primary.main,
      // contrastText: will be calculated to contrast with palette.primary.main
    },
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          '&.Mui-disabled': {
            backgroundColor: alpha('#1B2E45', 0.6),
            color: '#fff',
          },
        },  }}}
});

interface SubmitButtonProps {
  isAvailable: boolean,
}

const SubmitButton = ({isAvailable = false} : SubmitButtonProps) => {
  return (
    <ThemeProvider theme={theme}>
    <Button variant="contained" disableElevation endIcon={<Send size = {22}/>} fullWidth      color="primary" sx={{padding:'8px 22px'}} disabled = {!isAvailable}>Enviar</Button>
    </ThemeProvider>
  )
}

export default SubmitButton