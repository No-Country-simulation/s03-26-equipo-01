import { useForm } from "react-hook-form"
import "./newTestimonialForm.css"
import Box from "@mui/material/Box";
import Header from "./components/Header";
import { Input } from "./components/Input";
import AuthorizationCheckbox from "./components/AuthorizationCheckbox";
import SubmitButton from "./components/SubmitButton";
import { InputWithIcon } from "./components/InputWithIcon";
import { ImageUp, SquarePlay} from "lucide-react";
import { alpha } from "@mui/material/styles";
import UploadButtonWithIcon from "./components/UploadButtonWithIcon";

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
    }
  });
  return (
    <Box sx={{ width: '100%'}} >
      <Header title="Tu aprendizaje, en tus palabras" subtitle="Cuéntanos que lograste y cómo llegaste hasta aquí"/>
      
      <form onSubmit={()=>console.log('send')}>
        <Input 
          name="name" 
          label="Nombre y Apellido" 
          control={control}
          placeholder="Usa nombre real"
        />

        <Input 
          name="email" 
          label="Mail" 
          control={control} 
          placeholder="Registrado en la plataforma"
          rules={{ 
            required: "El email es necesario",
            pattern: { value: /^\S+@\S+$/i, message: "Mail no válido" }
          }}
        />

        <Input 
          name="course" 
          label="Curso o programa realizado" 
          control={control}
          placeholder="Indica nombre"
        />

        <Input 
          name="testimonial" 
          label="Testimonio" 
          control={control} 
          isMultiline={true}
          rows={6}
          placeholder="¿Qué fue lo que más te gustó de tu experiencia?"
        />

        <InputWithIcon control={control} label="Link video de Youtube (Opcional)" name="video" placeholder="https://www.youtube.com/watch..." icon = {<SquarePlay color={alpha("#2D2D2D", 0.5)} />} />
        
        <UploadButtonWithIcon control={control} label="Añadir imagen (Opcional)" name="image" icon={<ImageUp color={alpha("#2D2D2D", 0.5)} />} />

        <AuthorizationCheckbox name="authorization" control={control} text = "Autorizo el uso público de mi testimonio en la plataforma y materiales de comunicación."/>

        <SubmitButton isAvailable = {true}/>
  
      </form>
    </Box>
  )
}
