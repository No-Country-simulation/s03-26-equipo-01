import Paginator from "../../../../../../shared/components/pagination/Paginator"
import TableEditData from "../../../../../../shared/components/table-container/TableContainer"
import DischargeButton from "../dischard-button/DischargeButton"
import UnsuscribeButton from "../unsuscribe-button/UnsuscribeButton"
import tableData from "./table-data"
import type { UsersTableProps } from "./user-table"


const UsersTable = ({data, page, setPage, discharge, unsuscribe}: UsersTableProps) => {

    const isUserActive = (id: number) => tableData(data).rows.find(row => row.id === id)?.fields.includes('Activo');

    return (
        <>
            <TableEditData 
                tableData = {tableData(data)}>
                {(id: number) => 
                    (isUserActive(id) ? 
                        <DischargeButton onActive = {discharge} id = {id} /> : 
                        <UnsuscribeButton onActive = {unsuscribe} id = {id} />)}
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