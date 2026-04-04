import { useNavigate } from "react-router";
import type { User } from "../../user/models/user";
import { ADMIN_PATH, EDITOR_PATH, LOGIN_PATH } from "../../../core/routes/routes";
import { admin } from "../../user/models/roles";

const useUserNavegate = () => {

    const navegate = useNavigate();

    const redirectHomeTo = (user: User) => navegate(pathTo(user));

    const redirectMainTo = () => navegate(LOGIN_PATH);

    const pathTo = (user: User): string => user.rol == admin ? ADMIN_PATH : EDITOR_PATH;

    return {redirectMainTo, redirectHomeTo}
}

export default useUserNavegate;