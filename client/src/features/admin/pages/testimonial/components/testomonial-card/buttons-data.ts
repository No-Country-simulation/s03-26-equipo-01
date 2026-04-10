import type { ChangeStateButtons } from "../../../../../../shared/types/change-state-button-data/change-state-button"
import type { AdminState } from "../../../../models/state"

const buttonsStateData: Record<AdminState, ChangeStateButtons> = {
    Aprobado: {
        nextButtonName: 'PUBLICAR',
        prevButtonName: 'ELIMINAR'
    },
    Pendiente: {
        nextButtonName: 'APROBAR',
        prevButtonName: 'RECHAZAR'
    },
    Publicado: {
        nextButtonName: 'DESPUBLICAR',
        prevButtonName: 'ELIMINAR'
    }
}

export default buttonsStateData