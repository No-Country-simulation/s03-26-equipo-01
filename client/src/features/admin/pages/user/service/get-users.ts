import api from "../../../../../core/api/api";
import { ADMIN_USERS_CHARGE } from "../../../../../core/api/urls/urls";
import type { TablePaginator } from "../../../../../shared/types/table/table";
import type { EditableUser } from "../model/user";

async function getUsers(page: number = 0, size: number = 5): Promise<TablePaginator<EditableUser[]>> {
    const users = await api.get<TablePaginator<EditableUser[]>>(ADMIN_USERS_CHARGE, {params: {page, size}});
    return users.data;
}

export default getUsers;