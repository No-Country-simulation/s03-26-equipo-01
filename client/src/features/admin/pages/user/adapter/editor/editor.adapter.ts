import type { DataRow, TablePaginator } from "../../../../../../shared/types/table/table";
import type { EditableUser } from "../../model/editable-user";
import type { EditableUserResponse } from "./dto/editor-response";


function adapterTableEditors(response: TablePaginator<EditableUserResponse>): TablePaginator<EditableUser> {
    return {
        headers: response.headers,
        rows: adapterEditors(response.rows),
        page: response.page,
        size: response.size,
        totalElements: response.totalElements,
        totalPages: response.totalPages,
        last: response.last
    }
}

function adapterEditors(response: DataRow<EditableUserResponse>[]): DataRow<EditableUser>[] {
    return response.map(row => {
        return {
            id: row.id,
            data: adapterEditor(row.data)
        }
    })
}

function adapterEditor(response: EditableUserResponse): EditableUser {
    return {
        id: response.id, 
        email: response.mail, 
        testimonialsNumber: response.testimonialsNumber,
        enableName: response.enable ? 'Activo' : 'Inactivo',
        rol: response.Rol
    }
}

export default adapterTableEditors;