import api from "../../../core/api/api";
import { LOGIN_API } from "../../../core/api/urls";
import type { User } from "../../types/user";
import type { UserResponse } from "../adapters/dtos/user-response";
import userResponseAdapter from "../adapters/user-response";
import type { UserCredentials } from "../types/user-credentials";


async function loginService(credentials: UserCredentials): Promise<User> {
    try {
        const user = await api.post<UserResponse>(LOGIN_API, credentials);
        return userResponseAdapter(user.data);
    }
    catch(error: unknown) {
        console.log(error);
        throw new Error('Error al iniciar sesión');
    }
}

export default loginService;