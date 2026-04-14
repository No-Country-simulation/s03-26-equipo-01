import Paginator from "../../../../../../shared/components/pagination/Paginator"
import TableEditData from "../../../../../../shared/components/table-container/TableContainer"
import DeleteButton from "../../../../../../shared/elements/delete-button/DeleteButton"
import EditButton from "../../../../../../shared/elements/edit-button/EditButton"
import tableData from "./table-data"
import type { UsersTableProps } from "./user-table"


const UsersTable = ({data, page, setPage, discharge, unsuscribe}: UsersTableProps) => {
    return (
        <>
            <TableEditData 
                tableData = {tableData(data)}>
                {(id: number) => (
                    <>
                        <EditButton onSubmit = {discharge} id={id} />
                        <DeleteButton onSubmit = {unsuscribe} id={id} />
                    </>
                )}
            </TableEditData>
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