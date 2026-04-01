import { ADMIN_DASHBOARD_PATH, ADMIN_EMBED_PATH, ADMIN_TESTIMONIAL_PATH, ADMIN_USER_PATH } from "../../core/routes/routes";
import type { NavegationItemData } from "../../shared/types/navegation-list-data";


const itemsData: NavegationItemData[] = [
    {
        id: 1,
        iconUrl: '../../../public/dashboard-icon/dashboard-icon.svg',
        title: 'Dashboard',
        routePage: ADMIN_DASHBOARD_PATH,
        isDropdown: false,
    },
    {
        id: 2,
        iconUrl: '../../../public/testimonial-icon/testimonial-icon.svg',
        title: 'Testimonios',
        routePage: ADMIN_TESTIMONIAL_PATH,
        isDropdown: false,
    },
    {
        id: 3,
        iconUrl: '../../../public/manager-icon/manager-icon.svg',
        title: 'Gestión',
        routePage: ADMIN_TESTIMONIAL_PATH,
        isDropdown: false,
    },
    {
        id: 4,
        iconUrl: '../../../public/user-icon/user-icon.svg',
        title: 'Usuarios',
        routePage: ADMIN_USER_PATH,
        isDropdown: false,
    },
    {
        id: 5,
        iconUrl: '../../../public/embed-icon/embed-icon.svg',
        title: 'Embed',
        routePage: ADMIN_EMBED_PATH,
        isDropdown: true,
    }
];

export default itemsData;