import type { TableDataContent } from '../../../../types/table/table';

export interface BodyOneActionProps {
  tableData: TableDataContent;
  children: (id: number) => React.ReactNode
}
