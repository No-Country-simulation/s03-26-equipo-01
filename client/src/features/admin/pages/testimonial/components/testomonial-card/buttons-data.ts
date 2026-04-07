import type { ChangeStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { State } from "../../../../models/state"

const buttonsStateData: Record<State, ChangeStateButtons> = {
    APROBADO: {
        nextState: {
            text: 'PUBLICAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'ELIMINAR',
            event: (id: number) => console.log(id)
        }
    },
    BORRADOR: {
        nextState: {
            text: 'APROBAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'RECHAZAR',
            event: (id: number) => console.log(id)
        }
    },
    PUBLICADO: {
        nextState: {
            text: 'DESPUBLICAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'ELIMINAR',
            event: (id: number) => console.log(id)
        }
    },
    ARCHIVADO: {
        nextState: {
            text: 'APROBAR',
            event: (id: number) => console.log(id)
        },
        prevState: {
            text: 'RECHAZAR',
            event: (id: number) => console.log(id)
        }
    },
}

export default buttonsStateData