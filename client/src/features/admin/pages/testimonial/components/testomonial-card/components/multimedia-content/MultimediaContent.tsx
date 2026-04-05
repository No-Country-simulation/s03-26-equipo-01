import type { MultimediaContentProps } from "./multimedia-content";

const MultimediaContent = ({testimonial}: MultimediaContentProps) => {
    return (
        <section>
            <p>Contenido multimedia</p>
            <img 
                src = {testimonial.image?.url} 
                alt = {testimonial.image ? 'contenido multimedia del testimonio, o es una imagen o bien un vídeo' : ''} />
        </section>
    )
}

export default MultimediaContent;