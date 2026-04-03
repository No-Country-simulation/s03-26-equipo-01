import type { Rol } from "./rol"

export type User =  {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    rol: Rol
}