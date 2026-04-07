import type { TabValues } from "../../components/tab-container/tab-values"

export interface TabsProps {
    tabName: TabValues
    handleTab: (tabName: TabValues) => void
}