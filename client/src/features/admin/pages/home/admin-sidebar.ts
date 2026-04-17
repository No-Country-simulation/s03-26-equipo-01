import {
  ADMIN_ADD_TESTIMONIAL_EMBED_PATH,
  ADMIN_DASHBOARD_PATH,
  ADMIN_MANAGER_PATH,
  ADMIN_READ_TESTIMONIAL_EMBED_PATH,
  ADMIN_TESTIMONIAL_PATH,
  ADMIN_USER_PATH,
} from '../../../../core/routes/routes';
import type { NavegationItemData } from '../../../../shared/types/navegation-item-data/navegation-list-data';
import dashboardIcon from '../../../../assets/dashboard-icon/dashboard-icon.svg';
import testimonialsIcon from '../../../../assets/testimonial-icon/testimonial-icon.svg';
import managersIcon from '../../../../assets/manager-icon/manager-icon.svg';
import usersIcon from '../../../../assets/user-icon/user-icon.svg';
import embedIcon from '../../../../assets/embed-icon/embed-icon.svg';

const itemsData: NavegationItemData[] = [
  {
    id: 1,
    iconUrl: dashboardIcon,
    title: 'Dashboard',
    routePage: ADMIN_DASHBOARD_PATH,
    type: 'simple',
  },
  {
    id: 2,
    iconUrl: testimonialsIcon,
    title: 'Testimonios',
    routePage: ADMIN_TESTIMONIAL_PATH,
    type: 'simple',
  },
  {
    id: 3,
    iconUrl: managersIcon,
    title: 'Gestión',
    routePage: ADMIN_MANAGER_PATH,
    type: 'simple',
  },
  {
    id: 4,
    iconUrl: usersIcon,
    title: 'Usuarios',
    routePage: ADMIN_USER_PATH,
    type: 'simple',
  },
  {
    id: 5,
    iconUrl: embedIcon,
    title: 'Embed',
    type: 'dropdown',
    subRoutes: [
      {
        id: 6,
        title: 'Carrusel',
        routePage: ADMIN_READ_TESTIMONIAL_EMBED_PATH,
        type: 'simple',
      },
      {
        id: 7,
        title: 'Formulario',
        routePage: ADMIN_ADD_TESTIMONIAL_EMBED_PATH,
        type: 'simple',
      },
    ],
  },
];

export default itemsData;
