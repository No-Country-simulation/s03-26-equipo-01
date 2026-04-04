import type { SimpleNavegationItemData } from "../../../../../../types/navegation-item-data/simple";

export interface SimpleItemProps {
    item: SimpleNavegationItemData
    navegate: (url : string) => void
    handleActive: (id: number) => void
}