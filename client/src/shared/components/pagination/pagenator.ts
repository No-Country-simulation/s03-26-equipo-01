export default interface PaginatorProps {
  totalPages: number;
  currentPage: number;
  onPageChange: (page: number) => void;
  totalElements: number;
  pageSize: number;
}
