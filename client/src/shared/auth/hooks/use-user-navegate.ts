import { useNavigate } from "react-router";
import type { User } from "../../types/user/user";
import { admin } from "../../types/user/rol-names";
import { ADMIN_PATH, EDITOR_PATH } from "../../../core/routes/routes";

const useUserNavegate = () => {

    const navegate = useNavigate();

    function redirectTo(user: User): void {
        navegate(pathTo(user));
    }

    const pathTo = (user: User): string => user.rol == admin ? ADMIN_PATH : EDITOR_PATH;

    return {redirectTo}
}

export default useUserNavegate;