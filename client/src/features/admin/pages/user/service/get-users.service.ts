import api from "../../../../../core/api/api";
import { ADMIN_USERS_CHARGE } from "../../../../../core/api/urls/urls";
import type { TablePaginator } from "../../../../../shared/types/table/table";
import type { EditableUserResponse } from "../adapter/editor/dto/editor-response";
import adapterTableEditors from "../adapter/editor/editor.adapter";
import type { EditableUser } from "../model/editable-user";

async function getUsers(page: number = 0, size: number = 5): Promise<TablePaginator<EditableUser>> {
    const users = await api.get<TablePaginator<EditableUserResponse>>(ADMIN_USERS_CHARGE, {params: {page, size}});
    return adapterTableEditors(users.data);
}

export default getUsers;