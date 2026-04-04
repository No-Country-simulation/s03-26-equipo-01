import { Navigate, Outlet } from "react-router-dom";
import { LOGIN_PATH } from "../../../../core/routes/routes";
import { getTokenIs } from "../../../../core/services/token/get-token";

const AuthValidator = () => {

    const token = getTokenIs()

    if(!token) return <Navigate to = {LOGIN_PATH} />

    return <Outlet />
}

export default AuthValidator