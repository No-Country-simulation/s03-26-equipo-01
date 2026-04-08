import type { MultimediaContentProps } from "./multimedia-content";

const MultimediaContent = ({testimonial}: MultimediaContentProps) => {
    return (
        <section>
            <p>Contenido multimedia</p>
            <img 
                src = {testimonial.media.videoUrl} 
                alt = {testimonial.media.videoUrl ? 'contenido multimedia del testimonio, o es una imagen o bien un vídeo' : ''} />
        </section>
    )
}

export default MultimediaContent;