import type { TablePaginator } from "../../../../../../shared/types/table/table"
import type { EditableUser } from "../../model/editable-user"

export interface UsersTableProps {
    data: TablePaginator<EditableUser>
    page: number 
    setPage: (page: number) => void
    discharge: (id: number) => void
    unsuscribe: (id: number) => void
}