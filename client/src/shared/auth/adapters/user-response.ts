import type { User } from "../../types/user";
import type { UserResponse } from "./dtos/user-response";



function userResponseAdapter(response: UserResponse): User {
    return {
        id: response.id,
        email: response.email
    }
}

export default userResponseAdapter;