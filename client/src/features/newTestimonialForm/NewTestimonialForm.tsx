import { useForm } from "react-hook-form"
import "./newTestimonialForm.css"
import Box from "@mui/material/Box";
import Header from "./components/Header";
import { Input } from "./components/Input";

export const NewTestimonialForm = () => {
  const { control } = useForm({
    defaultValues: {
      name: "",
      email: "",
      testimonial: "",
      course:""
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

  
      </form>
    </Box>
  )
}
