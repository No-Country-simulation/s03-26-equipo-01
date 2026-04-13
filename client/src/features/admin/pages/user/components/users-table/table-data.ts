import type { TableDataContent, TablePaginator } from "../../../../../../shared/types/table/table";
import type { EditableUser } from "../../model/user";

const tableData = (table: TablePaginator<EditableUser>): TableDataContent => {
    return {
        headers: table.headers,
        rows: table.rows.map(row => {
            return {
                id: row.id,
                fields: Object.values(row.data)
            }
        })
    }
}

export default tableData;