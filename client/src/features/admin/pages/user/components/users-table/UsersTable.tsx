import Paginator from "../../../../../../shared/components/pagination/Paginator"
import TableEditData from "../../../../../../shared/components/table-container/TableContainer"
import type { TablePaginator } from "../../../../../../shared/types/table/table"
import type { EditableUser } from "../../model/editable-user"
import tableData from "./table-data"

interface UsersTableProps {
    data: TablePaginator<EditableUser>
    page: number 
    setPage: (page: number) => void
    discharge: (id: number) => void
    unsuscribe: (id: number) => void
}

const UsersTable = ({data, page, setPage, discharge, unsuscribe}: UsersTableProps) => {
    return (
        <>
            <TableEditData 
                tableData = {tableData(data)}
                activeEdit = {discharge}
                activeDelete = {unsuscribe}
            />
            <Paginator
                totalPages={data.totalPages}
                currentPage={page}
                onPageChange={setPage}
                totalElements={data.totalElements}
                pageSize={data.size}
            />
        </>
    )
}

export default UsersTable;