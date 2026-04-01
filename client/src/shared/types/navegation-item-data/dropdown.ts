import type { NavegationItem } from "./navegation-item";
import type { SimpleNavegationItemData } from "./simple";


/*
    MODEL: Modela un item de navegación desplegable. 
        - subRoutes: Lista de sub-rutas asociadas al item de navegación desplegable.
*/
export interface DropdownNavegationItemData extends NavegationItem {
    type: 'dropdown';
    subRoutes: SimpleNavegationItemData[];
}