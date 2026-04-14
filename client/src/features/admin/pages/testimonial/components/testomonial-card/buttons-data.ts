import type { ChangeStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminTestimonialState } from "../../../../models/state"

interface ButtonsStateData {
    openDiscartModal: (id: number) => void,
    openPublishModal: (id: number) => void,
    openAprovedModal: (id: number) => void,
    openDraftModal: (id: number) => void
}

const buttonsStateData = ({openDiscartModal, openPublishModal, openAprovedModal, openDraftModal}: ButtonsStateData): Record<AdminTestimonialState, ChangeStateButtons> => {
    return {
        Aprobado: {
            nextState: {
                textButton: 'PUBLICAR',
                event: openPublishModal
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: openDiscartModal
            }
        },
        Pendiente: {
            nextState: {
                textButton: 'APROBAR',
                event: openAprovedModal
            },
            discartTestimonial: {
                textButton: 'RECHAZAR',
                event: openDraftModal
            }
        },
        Publicado: {
            nextState: {
                textButton: 'DESPUBLICAR',
                event: openAprovedModal
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: openDiscartModal
            }  
        }
    }
}



export default buttonsStateData