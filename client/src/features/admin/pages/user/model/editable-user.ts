

type Enable = 'Activo' | 'Inactivo'
export interface EditableUser {
    id: number, 
    email: string 
    testimonialsNumber: number
    enableName: Enable
    rol: string
}
