import api from "../../../../core/api/api";
import { getUserData } from "../../../../core/services/token/decoded-token";
import getToken from "../../../../core/services/token/get-token";
import userDataAdapter from "../../adapters/user.adapter";
import type { UserResponse } from "../../dtos/user-response";
import type { User } from "../../models/user";

async function getUser(): Promise<User> {
    const tokenData = getUserData();
    const token = getToken();
    const userData = await api.get<UserResponse>(`/user/${tokenData.id}`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return userDataAdapter(userData.data, tokenData.role);
}

export default getUser;