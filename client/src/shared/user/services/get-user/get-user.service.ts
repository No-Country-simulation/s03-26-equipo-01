import api from "../../../../core/api/api";
import { USER_LOGED } from "../../../../core/api/urls/urls";
import { getUserData } from "../../../../core/services/token/decoded-token";
import userDataAdapter from "../../adapters/user.adapter";
import type { UserResponse } from "../../dtos/user-response";
import type { User } from "../../models/user";

async function getUser(): Promise<User> {
    const tokenData = getUserData();
    const userData = await api.get<UserResponse>(USER_LOGED);
    return userDataAdapter(userData.data, tokenData.role);
}

export default getUser;