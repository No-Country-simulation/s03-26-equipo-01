import type React from "react";
import type { TableDataContent } from "../../types/table/table";

export interface TableContainerProps {
  tableData: TableDataContent;
  customBody?: React.ReactNode;
  children: (id: number) => React.ReactNode
}