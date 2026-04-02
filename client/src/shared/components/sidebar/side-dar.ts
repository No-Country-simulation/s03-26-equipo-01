import type { NavegationItemData } from "../../types/navegation-item-data/navegation-list-data";


export interface SideBarProps {
    itemsData: NavegationItemData[]
    urlBase: string
    onActive: () => void 
    isActive: boolean
}
