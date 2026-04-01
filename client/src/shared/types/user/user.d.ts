import type { UserResponse } from "../../auth/adapters/dtos/user-response"
import type { Rol } from "./rol"


export type User = UserResponse & {
    rol: Rol
}