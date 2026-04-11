package com.cms.services.impl;

import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.ApiKey;
import com.cms.persistence.sql.ApiKeyDAOSQL;
import com.cms.services.ApiKeyService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ApiKeyServiceImpl implements ApiKeyService {

    private final PasswordEncoder passwordEncoder;
    private final ApiKeyDAOSQL apiKeyDAOSQL;

    public ApiKeyServiceImpl(PasswordEncoder passwordEncoder, ApiKeyDAOSQL apiKeyDAOSQ) {
        this.passwordEncoder = passwordEncoder;
        this.apiKeyDAOSQL = apiKeyDAOSQ;
    }

    @Override
    public ApiKey genereteApi(Admin admin) {
        String rawKey = "vza_" + UUID.randomUUID().toString().replace("-", "");

        return ApiKey.builder()
                .keyHash(rawKey)
                .prefix(rawKey.substring(0, 12))
                .active(true)
                .admin(admin)
                .build();
    }

    @Override
    public boolean validateApiKey(String rawKey) {
        if (rawKey == null || rawKey.length() < 12) return false;

        String prefix = rawKey.substring(0, 12);

        return apiKeyDAOSQL.findByPrefixAndActiveTrue(prefix)
                .map(apiKey -> apiKey.getKeyHash().equals(rawKey))
                .orElse(false);
    }
}
