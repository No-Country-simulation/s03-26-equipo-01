import type { User } from "../../types/user/user";
import type { UserResponse } from "./dtos/user-response";


export function userResponseAdapter(response: UserResponse): User {
    return {
        id: response.id,
        email: response.email,
        rol: response.role
    }
}
