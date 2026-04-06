import type { NavegationItem } from "./navegation-item";

/*
    MODEL: Modela un item de navegación simple. 
        - routePage: Ruta a la que redirige el item de navegación simple.
*/
export interface SimpleNavegationItemData extends NavegationItem {
    type: 'simple';
    routePage: string;
}
