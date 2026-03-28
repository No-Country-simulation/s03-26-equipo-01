import api from "../../../core/api/api";
import { LOGIN_API } from "../../../core/api/urls";
import type { User } from "../../types/user/user";
import type { UserCredentials } from "../models/user-credentials";
import { userResponseAdapter } from "../adapters/user.adapter";
import { setToken } from "../repository/token.repository";
import { CredentialsError } from "../../../core/api/errors/client-error/credentials-error";
import type { UserResponse } from "../adapters/dtos/user-response";


async function loginService(credentials: UserCredentials): Promise<User> {
    try {
        const user = await api.post<UserResponse>(LOGIN_API, credentials);
        setToken(user);
        return userResponseAdapter(user.data);
    }
    catch(error: unknown) {
        console.log(error);
        throw new CredentialsError();
    }
}

export default loginService;