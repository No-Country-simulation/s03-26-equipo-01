import type { ReactElement } from "react"
import type { TabValues } from "./tab-values"

export interface TabContainerProps {
    children: ReactElement<TabContent>[]
    onTab: (tabName: TabValues) => void
}

export interface TabContent {
    currentTab: TabValues
}