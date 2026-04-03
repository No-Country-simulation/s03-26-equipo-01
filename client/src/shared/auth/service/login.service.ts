import api from "../../../core/api/api";
import { LOGIN_API } from "../../../core/api/urls/urls";
import type { User } from "../../user/models/user";
import type { AuthCredentials } from "../models/user-credentials";
import { userResponseAdapter } from "../adapters/auth.adapter";
import type { UserResponse } from "../../user/dtos/user-response";
import handleInvalidCredentials from "./invalid-credentials";
import setToken from "../../../core/services/token/set-token";

async function loginService(credentials: AuthCredentials): Promise<User> {
    try {
        const user = await api.post<UserResponse>(LOGIN_API, credentials);
        setToken(user);
        return userResponseAdapter(user.data);
    }
    catch(error: unknown) {
        throw handleInvalidCredentials(error);
    }
}

export default loginService;