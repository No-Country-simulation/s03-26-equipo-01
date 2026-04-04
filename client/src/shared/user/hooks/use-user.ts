import { useEffect, useState } from "react";
import type { User } from "../models/user";
import getUser from "../services/get-user/get-user.service";
import useApi from "../../../core/api/hooks/use-api";

const useUser = () => {
    const {get} = useApi();
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        get(getUser)
            .then(user => setUser(user))
            .catch(error => console.log(error));
    }, []);

    return {user}
}

export default useUser;