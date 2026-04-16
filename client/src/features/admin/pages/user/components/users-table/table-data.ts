import type { TableDataContent, TablePaginator } from "../../../../../../shared/types/table/table";
import type { EditableUser } from "../../model/editable-user";

const tableData = (table: TablePaginator<EditableUser>): TableDataContent => {
    return {
        headers: table.headers,
        rows: table.rows.map(row => {
            return {
                id: row.id,
                fields: [row.data.id, row.data.email, row.data.rol, row.data.testimonialsNumber, row.data.enableName].map(value => value.toString())
            }
        })
    }
}

export default tableData;