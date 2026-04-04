package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.Category;
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

    private final EditorSQLDAO editorDAO;
    private final AdminSQLDAO adminDAO;
    private final CategorySQLDAO categoryDAO;

    public AdminServiceImpl(EditorSQLDAO editorDAO, AdminSQLDAO adminDAO, CategorySQLDAO categoryDAO) {
        this.editorDAO = editorDAO;
        this.adminDAO = adminDAO;
        this.categoryDAO = categoryDAO;
    }


    @Override
    public AdminResource getResource(Long idAdmin) {
        Admin admin = adminDAO.findById(idAdmin).orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));

        List<User> editores = editorDAO.findAllByCreatedBy(admin);
        List<Category> categories = categoryDAO.findAllByCreator(admin);

        return AdminResource.builder()
                .users(editores)
                .categories(categories)
                .build();
    }
}
