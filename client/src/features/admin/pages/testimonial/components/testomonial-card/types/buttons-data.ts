import type { SelectStateButtons } from "../../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminTestimonialState } from "../../../../../models/state"
import type { SelectState } from "./select-state"


const buttonsStateData = (selectTo: (id: number, state: SelectState) => void): Record<AdminTestimonialState, SelectStateButtons> => {
    return {
        Aprobado: {
            nextState: {
                textButton: 'PUBLICAR',
                event: (id: number) => selectTo(id, 'Publicado')
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: (id: number) => selectTo(id, 'Eliminado')
            }
        },
        Pendiente: {
            nextState: {
                textButton: 'APROBAR',
                event: (id: number) => selectTo(id, 'Aprobado')
            },
            discartTestimonial: {
                textButton: 'RECHAZAR',
                event: (id: number) => selectTo(id, 'Borrador')
            }
        },
        Publicado: {
            nextState: {
                textButton: 'DESPUBLICAR',
                event: (id: number) => selectTo(id, 'Aprobado')
            },
            discartTestimonial: {
                textButton: 'ELIMINAR',
                event: (id: number) => selectTo(id, 'Eliminado')
            }  
        }
    }
}



export default buttonsStateData