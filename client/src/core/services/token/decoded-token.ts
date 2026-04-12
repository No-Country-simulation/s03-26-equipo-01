import { jwtDecode } from "jwt-decode";
import type { Rol } from "../../../shared/user/models/rol";
import getToken from "./get-token";


export function getUserData(): UserTokenData {
    return jwtDecode<UserTokenData>(getToken());
}

export interface UserTokenData {
    id: number;
    role: Rol;
}