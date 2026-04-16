export interface TableDataContent {
  headers: string[];
  rows: Row[];
}

interface Row {
  id: number;
  fields: string[];
}

export interface TablePaginator<T> {
  headers: string[];
  rows: DataRow<T>[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}

export interface DataRow<T> {
  id: number;
  data: T;
  actions?: React.ReactNode;
}
