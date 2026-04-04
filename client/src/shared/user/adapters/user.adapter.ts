import type { UserResponse } from "../dtos/user-response";
import type { Rol } from "../models/rol";
import type { User } from "../models/user";

function userDataAdapter(user: UserResponse, rol: Rol): User {
    return {
        id: user.id,
        email: user.email,
        firstName: user.firstName,
        lastName: user.lastName,
        rol
    }
}

export default userDataAdapter;