import api from '../../../../core/api/api';
import { ADMIN_API_KEY_API } from '../../../../core/api/urls/urls';

type AdminApiKeyResponse = {
  apiKey: string;
};

async function adminApiKeyService(): Promise<string> {
  const response = await api.get<AdminApiKeyResponse>(ADMIN_API_KEY_API);
  return response.data.apiKey;
}

export default adminApiKeyService;
