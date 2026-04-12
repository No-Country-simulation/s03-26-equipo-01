import type { MultimediaContentProps } from "./multimedia-content";
import './styles/testimonial.css';

const MultimediaContent = ({testimonial}: MultimediaContentProps) => {
    return (
        <section className = 'testimonial-admin-media'>
            <p>Contenido multimedia</p>
            <a href = {testimonial.media.videoUrl} className = 'testimonial-admin-media_link'>
                <img 
                src = {testimonial.media.thumbnailUrl} 
                alt = {testimonial.media.thumbnailUrl ? 'contenido multimedia del testimonio, o es una imagen o bien un vídeo' : ''} />
                <span className="play-button"></span>
            </a>
        </section>
    )
}

export default MultimediaContent;