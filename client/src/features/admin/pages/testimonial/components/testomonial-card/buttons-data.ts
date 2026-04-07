import type { ChangeStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminState } from "../../../../models/state"

const buttonsStateData: Record<AdminState, ChangeStateButtons> = {
    Aprobado: {
        nextState: {
            text: 'PUBLICAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'ELIMINAR',
            event: (id: number) => console.log(id)
        }
    },
    Pendiente: {
        nextState: {
            text: 'APROBAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'RECHAZAR',
            event: (id: number) => console.log(id)
        }
    },
    Publicado: {
        nextState: {
            text: 'DESPUBLICAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'ELIMINAR',
            event: (id: number) => console.log(id)
        }
    }
}

export default buttonsStateData