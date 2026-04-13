import api from "../../../../../core/api/api";
import { USER_UNSUSCRIBE } from "../../../../../core/api/urls/urls";

async function unsuscribeService(id: number): Promise<void> {
    await api.patch(USER_UNSUSCRIBE(id));
} 

export default unsuscribeService;