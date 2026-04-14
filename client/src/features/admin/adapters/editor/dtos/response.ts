import type { Rol } from "../../../../../shared/user/models/rol"

export interface EditorResponse {
    id: number,
    mail: string,
    Rol: Rol,
    testimonialsNumber: number,
    enable: boolean
}