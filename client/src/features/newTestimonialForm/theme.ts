import { alpha } from '@mui/material/styles';
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: alpha("#21140F", 1), 
    },
    secondary: {
      main: alpha("#21140F", 0.75),
    },
    background: {
      default: '#FAFAFA',
    },
  },

  components: {
    MuiButton: {
      defaultProps: {
        disableElevation: true, 
      },
      styleOverrides: {
        containedPrimary: {
          backgroundColor: alpha('#1B2E45', 1), 
          color: '#ffffff',           
          '&:hover': {
            backgroundColor: alpha('#152538', 1),
          },
        },
        root: {
          '&.Mui-disabled': {
            backgroundColor: alpha('#1B2E45', 0.60), 
            color: '#FFFFFF',           
          },
        },
      }
    },
    MuiCheckbox: {
      styleOverrides: {
        root: {
          '&.Mui-checked': {
            color: '#1E2B45', 
           },
        },
      },
    },
    MuiFormLabel: {
      styleOverrides:{
        root:({ theme }) => ({
          color: theme.palette.secondary.main,
        }),
      }
    },
    MuiInput: {
  styleOverrides: {
    root: {
      '& input::placeholder': {
        color: alpha('#21140F', 0.46),
        opacity: 1
      },
    },
  },
},
  }
});

export default theme;