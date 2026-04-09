import type { TableDataContent } from "../../types/table/table";

export interface TableContainerProps {
    tableData: TableDataContent
    activeEdit: (id: number) => void
    activeDelete: (id: number) => void
}

export interface HeaderProps {
    tableData: TableDataContent
}