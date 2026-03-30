import { useForm } from "react-hook-form"
import "./newTestimonialForm.css"
import Container from "@mui/material/Container";
import Box from "@mui/material/Box";
import Header from "./components/Header";
import { TextInput } from "./components/TextInput";
import AuthorizationCheckbox from "./components/AuthorizationCheckbox";
import SubmitButton from "./components/SubmitButton";
import { TextInputWithIcon } from "./components/TextInputWithIcon";
import { ImageUp, SquarePlay} from "lucide-react";
import { alpha, ThemeProvider } from "@mui/material/styles";
import UploadButtonWithIcon from "./components/UploadButtonWithIcon";
import RatingPicker from "./components/RatingPicker";
import { MultitextInput } from "./components/MultiTextInput";
import theme from "./theme";

export const NewTestimonialForm = () => {
  const { control } = useForm({
    defaultValues: {
      name: "",
      email: "",
      testimonial: "",
      course:"",
      authorization: false,
      video: "",
      image: null,
      rating: null
    }
  });
  return (
    <ThemeProvider theme={theme}>
    <Container>
    <Box sx={{ width: '100%', minWidth:"440px", backgroundColor:"#FAFAFA", padding:"48px 12px", display:"flex", justifyContent:"center"}} >
        <Box component="form" sx={{ display:"flex", flexDirection:"column", width: '100%', maxWidth:"800px" , backgroundColor:"#FFFFFF", gap:"10px", padding:"48px 12px", borderRadius:"16px", border: `1px solid ${alpha('#464646', 0.22)}`}} onSubmit={()=>console.log('send')}>
          <Box sx={{display:"flex", flexDirection:"column", gap:"48px", padding:"0 24px"}}>
            <Header title="Tu aprendizaje, en tus palabras" subtitle="Cuéntanos que lograste y cómo llegaste hasta aquí"/>

            <Box sx={{display:"flex", justifyContent:"space-between", gap:"45px"}}>
              <TextInput 
                name="name" 
                label="Nombre y Apellido" 
                control={control}
                placeholder="Usa nombre real"
              />

              <TextInput 
                name="email" 
                label="Mail" 
                control={control} 
                placeholder="Registrado en la plataforma"
                rules={{ 
                  required: "El email es necesario",
                  pattern: { value: /^\S+@\S+$/i, message: "Mail no válido" }
                }}
              />
            </Box>
            <Box sx={{display:"flex", flexWrap:"wrap", gap:"45px"}}>
              <TextInput 
                name="course" 
                label="Curso o programa realizado" 
                control={control}
                placeholder="Indica nombre"
              />

              <RatingPicker name="rating" label="Valoración general" control={control} />
            </Box>
        </Box>
        <Box sx={{display:"flex", flexDirection:"column", gap:"64px", padding:"24px 24px"}}>
          <Box sx={{display:"flex", flexDirection:"column", gap:"45px", padding:"0"}}>
            <MultitextInput
              name="testimonial" 
              label="Testimonio" 
              control={control} 
              rows={6}
              placeholder="¿Qué fue lo que más te gustó de tu experiencia?"
            />

            <Box sx={{display:"flex", justifyContent:"space-between", flexWrap: "wrap", gap:"45px"}}>
              <TextInputWithIcon control={control} label="Link video de Youtube (Opcional)" name="video" placeholder="https://www.youtube.com/watch..." icon = {<SquarePlay color={alpha("#2D2D2D", 0.5)} />} />
              
              <UploadButtonWithIcon control={control} label="Añadir imagen (Opcional)" name="image" icon={<ImageUp color={alpha("#2D2D2D", 0.5)} />} />
            </Box>

            <AuthorizationCheckbox name="authorization" control={control} text = "Autorizo el uso público de mi testimonio en la plataforma y materiales de comunicación."/>

            <SubmitButton isAvailable = {true}/>
          </Box>
        </Box>
      
      </Box>
    </Box>
    </Container>
    </ThemeProvider>
  )
}
