import type { DropdownNavegationItemData } from "../../../../types/navegation-item-data/dropdown";
import type { NavegationItemData } from "../../../../types/navegation-item-data/navegation-list-data";
import type { SimpleNavegationItemData } from "../../../../types/navegation-item-data/simple";

export interface NavegationItemProps {
    item: NavegationItemData
    urlBase: string
    handleActive: (id: number) => void
    isElementActive: (id: number) =>boolean
}

export interface SimpleNavItemProps {
    item: SimpleNavegationItemData
    handleNavegate: (url: string) => void
    handleActive: (id: number) => void
    isElementActive: (id: number) =>boolean
}

export interface DropNavItemProps {
    item: DropdownNavegationItemData
    handleNavegate: (url: string) => void
    handleActive: (id: number) => void
    isElementActive: (id: number) => boolean
}