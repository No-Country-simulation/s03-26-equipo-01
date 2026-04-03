import type { ReactNode } from "react"
import type { NavegationItemData } from "../../types/navegation-item-data/navegation-list-data"

export interface PageContainerProps {
    itemsData: NavegationItemData[]
    basePath: string
    children: ReactNode
}

export interface HeaderContainerProps {
    onActive: () => void
    isActive: boolean
}

export interface PageContentProps {
    itemsData: NavegationItemData[]
    onActive: () => void
    isActive: boolean
    basePath: string
    children: ReactNode
}