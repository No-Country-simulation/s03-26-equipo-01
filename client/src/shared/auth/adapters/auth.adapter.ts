import type { User } from "../../user/models/user";
import type { UserResponse } from "../../user/dtos/user-response";
import { getUserData } from "../../../core/services/token/decoded-token";

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
