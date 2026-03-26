import type { Rol } from "./rol"


export interface User {
    id: number
    email: string 
    type: Rol
}