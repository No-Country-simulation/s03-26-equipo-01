import type { DropdownNavegationItemData } from "./dropdown";
import type { SimpleNavegationItemData } from "./simple";


/*
    MODEL: Modela un item de navegación simple o desplegable. 
        - type: Indica el tipo de item de navegación, solo puede ser 'simple' o 'dropdown'.
        - id: Identificador único del item de navegación.
        - iconUrl: URL del icono asociado al item de navegación.
        - title: Título del item de navegación.
*/
export type NavegationItemData = SimpleNavegationItemData | DropdownNavegationItemData;

