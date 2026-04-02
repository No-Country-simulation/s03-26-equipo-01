import SideBar from '../../../shared/components/sidebar/Sidebar';
import itemsData from './admin-sidebar';
import './admin.css';
import AdminRoutes from '../routes/admin-routes';
import { ADMIN_BASE_PATH } from '../../../core/routes/routes';

const Admin = () => {
    
    return (
        <main className = 'admin-page'>
            <SideBar 
                itemsData = {itemsData} 
                urlBase = {ADMIN_BASE_PATH} 
            />
            <AdminRoutes />
        </main>
    )
}

export default Admin;