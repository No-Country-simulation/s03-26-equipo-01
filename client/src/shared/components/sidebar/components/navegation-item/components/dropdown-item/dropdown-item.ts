import type { DropdownNavegationItemData } from "../../../../../../types/navegation-item-data/dropdown";


export interface DropDownItemProps {
    item: DropdownNavegationItemData
    navegate: (url : string) => void
    onActive: (id: number) => void
}

export interface DropDownContainerProps {
    item: DropdownNavegationItemData
    isActive: boolean
}

export interface DropDownListProps {
    item: DropdownNavegationItemData
    navegate: (url : string) => void
    onActive: (id: number) => void
}

