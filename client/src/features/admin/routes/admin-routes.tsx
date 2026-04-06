import { Route, Routes } from "react-router-dom"
import { ADMIN_ADD_TESTIMONIAL_EMBED_PATH, ADMIN_DASHBOARD_PATH, ADMIN_MANAGER_PATH, ADMIN_READ_TESTIMONIAL_EMBED_PATH, ADMIN_TESTIMONIAL_PATH, ADMIN_USER_PATH } from '../../../core/routes/admin/admin';
import TestimonialPanel from "../pages/testimonial/TestimonialPanel";
import DashboardPanel from "../pages/dashboard/DashboardPanel";

const AdminRoutes = () => {
    
    return (
        <Routes>
            <Route path = {ADMIN_DASHBOARD_PATH} element = {<DashboardPanel />} />
            <Route path = {ADMIN_TESTIMONIAL_PATH} element = {<TestimonialPanel />} />
            <Route path = {ADMIN_MANAGER_PATH} element = {<p>gestion</p>} />
            <Route path = {ADMIN_USER_PATH} element = {<p>user</p>} />
            <Route path = {ADMIN_ADD_TESTIMONIAL_EMBED_PATH} element = {<p>create embed</p>} />
            <Route path = {ADMIN_READ_TESTIMONIAL_EMBED_PATH} element = {<p>read embed</p>} />
        </Routes>
    )
}

export default AdminRoutes;