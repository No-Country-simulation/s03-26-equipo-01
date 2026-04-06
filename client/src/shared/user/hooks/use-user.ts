import { useState } from "react";
import type { User } from "../models/user";
import getUser from "../services/get-user/get-user.service";
import useApi from "../../../core/api/hooks/use-api";

const useUser = () => {
    const {get} = useApi();
    const [user, setUser] = useState<User | null>(null);

    const findUser = () => {
        get(getUser)
            .then(user => setUser(user))
            .catch(error => error)
    }

    const saveUser = (user: User) => setUser(user);

    const refreshUser = () => setUser(null);

    return {user, findUser, saveUser, refreshUser}
}

export default useUser;