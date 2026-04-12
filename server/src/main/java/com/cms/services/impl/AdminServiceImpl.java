package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.services.AdminService;
import com.cms.services.ApiKeyService;
import com.cms.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminSQLDAO adminDAO;

    private final UserService userService;

    private final ApiKeyService apiKeyService;

    public AdminServiceImpl(AdminSQLDAO adminDAO, UserService userService, ApiKeyService apiKeyService) {
        this.adminDAO = adminDAO;
        this.userService = userService;
        this.apiKeyService = apiKeyService;
    }


    @Override
    public AdminResource getResource(Long idAdmin) {
        Admin admin = adminDAO.findById(idAdmin)
                .orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));

        return AdminResource.builder()
                .users(admin.getEditors())
                .categories(admin.getCategories())
                .tags(admin.getTags())
                .build();
    }

    @Override
    public Admin save(Admin admin) {
        userService.save(admin);

        ApiKey apiKeyAdmin = apiKeyService.genereteApi(admin);

        admin.setApiKey(apiKeyAdmin);
        adminDAO.save(admin);

        return admin;
    }

    @Override
    public ApiKey getApiKey(Long idAdmin) {
        Admin admin = adminDAO.findById(idAdmin)
                .orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));

        return admin.getApiKey();
    }
}
