import type { User } from "../../types/user/user";
import { getUserData } from "../repository/token.repository";
import type { UserResponse } from "./dtos/user-response";

export function userResponseAdapter(response: UserResponse): User {
    const userHeaderData = getUserData();
    return {
        email: response.email,
        firstName: response.firstName,
        lastName: response.lastName,
        id: response.id,
        rol: userHeaderData.role
    }
}
