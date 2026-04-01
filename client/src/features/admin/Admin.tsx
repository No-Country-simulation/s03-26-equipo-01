import SideBar from '../../shared/components/sidebar/Sidebar';
import itemsData from './admin-sidebar';
import './admin.css';

const Admin = () => {
    return (
        <main className = 'admin-page'>
            <SideBar itemsData = {itemsData} />
        </main>
    )
}

export default Admin;