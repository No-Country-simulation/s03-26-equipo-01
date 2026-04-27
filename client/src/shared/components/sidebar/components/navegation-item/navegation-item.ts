import type { DropdownNavegationItemData } from "../../../../types/navegation-item-data/dropdown";
import type { NavegationItemData } from "../../../../types/navegation-item-data/navegation-list-data";
import type { SimpleNavegationItemData } from "../../../../types/navegation-item-data/simple";

export interface NavegationItemProps {
    item: NavegationItemData
    urlBase: string
    isRouteActive: (routePage: string) => boolean
}

export interface SimpleNavItemProps {
    item: SimpleNavegationItemData
    handleNavegate: (url: string) => void
    isSelected: boolean
}

export interface DropNavItemProps {
    item: DropdownNavegationItemData
    handleNavegate: (url: string) => void
    isRouteActive: (routePage: string) => boolean
    isSelected: boolean
}
