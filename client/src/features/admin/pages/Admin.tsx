import itemsData from './admin-sidebar';
import AdminRoutes from '../routes/admin-routes';
import { ADMIN_BASE_PATH } from '../../../core/routes/routes';
import PageContainer from '../../../shared/components/page-container/PageContainer';

const Admin = () => {
    
    return (
        <PageContainer 
            itemsData = {itemsData} 
            basePath = {ADMIN_BASE_PATH}
        >
            <AdminRoutes />
        </PageContainer>
    )
}

export default Admin;