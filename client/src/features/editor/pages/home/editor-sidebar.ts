import {
  EDITOR_TESTIMONIAL_BANK,
  EDITOR_TESTIMONIAL,
} from '../../../../core/routes/routes';
import type { NavegationItemData } from '../../../../shared/types/navegation-item-data/navegation-list-data';
import dashboardIcon from '../../../../assets/dashboard-icon/dashboard-icon.svg';
import testimonialsIcon from '../../../../assets/testimonial-icon/testimonial-icon.svg';

const itemsData: NavegationItemData[] = [
  {
    id: 1,
    iconUrl: dashboardIcon,
    title: 'Banco de testimonios',
    routePage: EDITOR_TESTIMONIAL_BANK,
    type: 'simple',
  },
  {
    id: 2,
    iconUrl: testimonialsIcon,
    title: 'Mis Testimonios',
    routePage: EDITOR_TESTIMONIAL,
    type: 'simple',
  },
];

export default itemsData;
