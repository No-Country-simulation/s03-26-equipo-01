import type { DropdownNavegationItemData } from "../../../../../../types/navegation-item-data/dropdown";


export interface DropDownItemProps {
    item: DropdownNavegationItemData
    navegate: (url : string) => void
    isRouteActive: (routePage: string) => boolean
}

export interface DropDownContainerProps {
    item: DropdownNavegationItemData
    isActive: boolean
}

export interface DropDownListProps {
    item: DropdownNavegationItemData
    navegate: (url : string) => void
    isRouteActive: (routePage: string) => boolean
}

