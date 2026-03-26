import type { Rol } from "../../../types/user/rol"


export interface UserResponse {
    role: Rol
    token: string
    type: string
    id: number
    email: string 
}

