import type { ReactNode } from "react";
import type { Table } from "../../types/table/table";

export interface TableContainerProps {
    tableData: Table
    children: ReactNode
}
