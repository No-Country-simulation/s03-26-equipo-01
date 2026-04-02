import { Route, Routes } from "react-router-dom"
import { ADMIN_ADD_TESTIMONIAL_EMBED_PATH, ADMIN_DASHBOARD_PATH, ADMIN_MANAGER_PATH, ADMIN_READ_TESTIMONIAL_EMBED_PATH, ADMIN_TESTIMONIAL_PATH, ADMIN_USER_PATH } from '../../../core/routes/admin/admin';

const AdminRoutes = () => {
    
    return (
        <Routes>
            <Route path = {ADMIN_DASHBOARD_PATH} element = {<p>dashboard</p>} />
            <Route path = {ADMIN_TESTIMONIAL_PATH} element = {<p>test</p>} />
            <Route path = {ADMIN_MANAGER_PATH} element = {<p>gestion</p>} />
            <Route path = {ADMIN_USER_PATH} element = {<p>user</p>} />
            <Route path = {ADMIN_ADD_TESTIMONIAL_EMBED_PATH} element = {<p>create embed</p>} />
            <Route path = {ADMIN_READ_TESTIMONIAL_EMBED_PATH} element = {<p>read embed</p>} />
        </Routes>
    )
}

export default AdminRoutes;