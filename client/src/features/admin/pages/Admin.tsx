import SideBar from '../../../shared/components/sidebar/Sidebar';
import itemsData from './admin-sidebar';
import './admin.css';
import AdminRoutes from '../routes/admin-routes';
import { ADMIN_BASE_PATH } from '../../../core/routes/routes';
import { useState } from 'react';

const Admin = () => {
    const [isActive, setIsActive] = useState(false);
        
    const handleActive = () => setIsActive(!isActive);
    
    return (
        <main className = {isActive ? 'admin-page_reduce' : 'admin-page'}>
            <SideBar 
                itemsData = {itemsData} 
                urlBase = {ADMIN_BASE_PATH} 
                isActive = {isActive}
                onActive = {handleActive}
            />
            <AdminRoutes />
        </main>
    )
}

export default Admin;