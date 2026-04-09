import type { ReactNode } from "react";
import type { TableDataContent } from "../../types/table/table";

export interface TableContainerProps {
    tableData: TableDataContent
    children: ReactNode
}

export interface HeaderProps {
    tableData: TableDataContent
}

export interface BodyProps {
    tableData: TableDataContent
    children: ReactNode
}