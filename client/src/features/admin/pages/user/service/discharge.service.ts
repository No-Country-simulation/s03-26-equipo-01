import api from "../../../../../core/api/api";
import { USER_DISCHARGE } from "../../../../../core/api/urls/urls";

async function dischargeService(id: number): Promise<void> {
    await api.patch(USER_DISCHARGE(id));
} 

export default dischargeService;