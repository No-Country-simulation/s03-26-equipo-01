import api from "../../../../../core/api/api";
import { ADMIN_CREATED_USER } from "../../../../../core/api/urls/urls";
import type { CreatedUser } from "../model/created-user";
import type { EditableUser } from "../model/editable-user";


async function createService(newUser: CreatedUser): Promise<EditableUser> {
    const editor = await api.post<EditableUser>(ADMIN_CREATED_USER, newUser);
    return editor.data;
}

export default createService;