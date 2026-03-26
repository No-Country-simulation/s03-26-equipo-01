import type { Rol } from "../../types/user/rol";
import { admin, editor } from "../../types/user/rol-names";
import type { User } from "../../types/user/user";
import type { UserResponse } from "./dtos/user-response";


export function userResponseAdapter(response: UserResponse): User {
    return {
        id: response.id,
        email: response.email,
        type: createUserRol(response.type)
    }
}

function createUserRol(type: string): Rol {
    return type === admin ? admin : editor;
}