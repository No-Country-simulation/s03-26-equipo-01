import Paginator from "../../../../../../shared/components/pagination/Paginator"
import TableEditData from "../../../../../../shared/components/table-container/TableContainer"
import tableData from "./table-data"
import type { UsersTableProps } from "./user-table"


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