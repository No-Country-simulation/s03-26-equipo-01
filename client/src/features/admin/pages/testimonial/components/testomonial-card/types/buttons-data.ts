import type { ChangeStateButtons } from "../../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminTestimonialState } from "../../../../../models/state"

interface ButtonsStateData {
    changeToDiscart: (id: number) => void,
    changeToPublished: (id: number) => void,
    changeToAproved: (id: number) => void,
    changeToDraft: (id: number) => void
}

const buttonsStateData = ({changeToDiscart, changeToPublished, changeToAproved, changeToDraft}: ButtonsStateData): Record<AdminTestimonialState, ChangeStateButtons> => {
    return {
        Aprobado: {
            nextState: {
                textButton: 'PUBLICAR',
                event: changeToPublished
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: changeToDiscart
            }
        },
        Pendiente: {
            nextState: {
                textButton: 'APROBAR',
                event: changeToAproved
            },
            discartTestimonial: {
                textButton: 'RECHAZAR',
                event: changeToDraft
            }
        },
        Publicado: {
            nextState: {
                textButton: 'DESPUBLICAR',
                event: changeToAproved
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: changeToDiscart
            }  
        }
    }
}



export default buttonsStateData