import type { TableDataContent } from "../../../../types/table/table"

export interface BodyOneActionProps {
    tableData: TableDataContent
    activeEdit: (id: number) => void
    activeDelete: (id: number) => void
}