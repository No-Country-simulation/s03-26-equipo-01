import type { ToastData } from "../../../../../../../shared/types/toast-data/toast-data"
import type { ChangeStateResult } from "../types/change-state-result"
import type { SelectState } from "../types/select-state"

const toastData = (result: ChangeStateResult): ToastData => {
    if (result.isError) return { title: 'Error', message: 'Hubo un error al cambiar el estado del testimonio', type: 'error' }
    return {
        ...toastSuccesText(result.state), 
        type: 'success'
    } 
}

const toastSuccesText = (state: SelectState): Omit<ToastData, 'type'> => {
    switch(state) {
        case 'Aprobado':
            return {
                title: 'Testimonio Aprobado'
            }
        case 'Borrador':
            return {
                title: 'Testimonio Rechazado',
                message: 'Lo devolvimos al editor'
            }
        case 'Publicado':
            return {
                title: 'Testimonio Publicado'
            }
        case 'Pendiente':
            return {
                title: 'Testimonio Pendiente'
            }
        case 'Eliminado':
            return {
                title: 'Testimonio Eliminado',
                message: 'Testimonio eliminado correctamente'
            }
    }
}

export default toastData;