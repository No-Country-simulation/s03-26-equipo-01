import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography"

type HeaderPropsType = {
  title: string,
  subtitle: string
}

const Header = ({title, subtitle}: HeaderPropsType) => {
  return (
    <Box sx={{ 
      display: 'flex',
      flexDirection:'column' ,
      alignItems: 'center',
      width: '100%'
    }}>
      <Typography fontWeight="fontWeightRegular" variant="h4" color="primary" align="center" sx={{fontSize:"3.2rem"}}>{title}</Typography>
      <Typography variant="subtitle1" color="primary" align="center" sx={{fontSize:"1.6rem"}}>{subtitle}</Typography>
    </Box>
  )
}

export default Header;
