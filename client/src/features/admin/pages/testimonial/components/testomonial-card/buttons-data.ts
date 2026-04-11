import type { ChangeStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminTestimonialState } from "../../../../models/state"

const buttonsStateData = (nextState: (id: number) => void, discart: (id: number) => void): Record<AdminTestimonialState, ChangeStateButtons> => {
    return {
        Aprobado: {
            nextState: {
                textButton: 'PUBLICAR',
                event: nextState
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: discart
            }
        },
        Pendiente: {
            nextState: {
                textButton: 'APROBAR',
                event: nextState
            },
            discartTestimonial: {
                textButton: 'RECHAZAR',
                event: nextState
            }
        },
        Publicado: {
            nextState: {
                textButton: 'DESPUBLICAR',
                event: nextState
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event:discart
            }  
        }
    }
}



export default buttonsStateData