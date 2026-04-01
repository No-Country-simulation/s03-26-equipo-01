import { ADMIN_DASHBOARD_PATH } from "../../core/routes/routes";
import type { NavegationItemData } from "../../shared/types/navegation-list-data";


const itemsData: NavegationItemData[] = [
    {
        id: 1,
        iconUrl: '../../../public/dashboard-icon.svg',
        title: 'Dashboard',
        routePage: ADMIN_DASHBOARD_PATH,
        isDropdown: false,
    }
];

export default itemsData;