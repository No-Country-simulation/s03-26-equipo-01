import type { DropdownNavegationItemData } from "../../../../../../types/navegation-item-data/dropdown";


export interface DropDownItemProps {
    item: DropdownNavegationItemData
    navegate: (url : string) => void
}