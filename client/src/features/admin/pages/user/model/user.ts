import type { Rol } from "../../../../../shared/user/models/rol"

export interface EditableUser {
    id: number, 
    email: string 
    rol: Rol
    testimonialsNumber: number
    enable: boolean
}
