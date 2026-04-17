import api from "../../../../../core/api/api";
import { USER_UNSUSCRIBE } from "../../../../../core/api/urls/urls";
import type { EditableUserResponse } from "../adapter/editor/dto/editor-response";
import { adapterEditor } from "../adapter/editor/editor.adapter";
import type { EditableUser } from "../model/editable-user";

async function unsuscribeService(id: number): Promise<EditableUser> {
    const editableUser = await api.delete<EditableUserResponse>(USER_UNSUSCRIBE(id));
    return adapterEditor(editableUser.data)
} 

export default unsuscribeService;