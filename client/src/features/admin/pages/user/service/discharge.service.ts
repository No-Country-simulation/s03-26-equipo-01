import api from "../../../../../core/api/api";
import { USER_DISCHARGE } from "../../../../../core/api/urls/urls";
import type { EditableUserResponse } from "../adapter/editor/dto/editor-response";
import { adapterEditor } from "../adapter/editor/editor.adapter";
import type { EditableUser } from "../model/editable-user";

async function dischargeService(id: number): Promise<EditableUser> {
    const editableUser = await api.patch<EditableUserResponse>(USER_DISCHARGE(id));
    return adapterEditor(editableUser.data)
} 

export default dischargeService;