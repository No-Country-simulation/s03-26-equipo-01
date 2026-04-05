package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.model.user.User;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.CategorySQLDAO;
import com.cms.persistence.sql.EditorSQLDAO;
import com.cms.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminSQLDAO adminDAO;

    public AdminServiceImpl(AdminSQLDAO adminDAO) {
        this.adminDAO = adminDAO;
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
}
