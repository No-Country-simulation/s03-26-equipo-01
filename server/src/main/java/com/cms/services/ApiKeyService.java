package com.cms.services;

import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.ApiKey;

public interface ApiKeyService {
    ApiKey genereteApi(Admin admin);

    boolean validateApiKey(String rawKey);
}
