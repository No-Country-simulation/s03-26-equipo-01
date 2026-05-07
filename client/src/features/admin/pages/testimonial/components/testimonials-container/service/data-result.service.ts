import type { ToastData } from "../../../../../../../shared/types/toast-data/toast-data"
import type { ChangeStateResult } from "../types/change-state-result"

const toastData = (result: ChangeStateResult): ToastData => {
    if (!result) return { message: '', type: 'info' }
    if (result.isError) return { message: 'Hubo un error al cambiar el estado del testimonio', type: 'error' }
    return { message: `El testimonio se ha cambiado al estado ${result.state} exitosamente`, type: 'success'} 
}

export default toastData;