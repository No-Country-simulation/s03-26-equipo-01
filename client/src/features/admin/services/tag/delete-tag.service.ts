import api from "../../../../core/api/api";
import { DELETE_TAG_API } from "../../../../core/api/urls/urls";

async function deleteTagService(id?: number): Promise<void> {
    await api.delete(`${DELETE_TAG_API}/${id}`);
}

export default deleteTagService;