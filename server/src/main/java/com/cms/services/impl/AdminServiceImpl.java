package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.services.AdminService;
import com.cms.services.ApiKeyService;
import com.cms.services.TestimonialService;
import com.cms.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminSQLDAO adminDAO;

    private final UserService userService;

    private final ApiKeyService apiKeyService;

    private final TestimonialService testimonialService;

    public AdminServiceImpl(AdminSQLDAO adminDAO, UserService userService, ApiKeyService apiKeyService, TestimonialService testimonialService) {
        this.adminDAO = adminDAO;
        this.userService = userService;
        this.apiKeyService = apiKeyService;
        this.testimonialService = testimonialService;
    }


    @Override
    public AdminResource getResource(Long idAdmin) {
        Admin admin = getAdmin(idAdmin);

        return AdminResource.builder()
                .users(admin.getEditors())
                .categories(admin.getCategories())
                .tags(admin.getTags())
                .build();
    }

    private Admin getAdmin(Long idAdmin) {
        return adminDAO.findById(idAdmin)
                .orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));
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
    public Editor createEditor(Editor modelEditor, Long idAdmin) {
        Admin admin = getAdmin(idAdmin);

        modelEditor.setCreatedBy(admin);

        return (Editor) userService.save(modelEditor);
    }

    @Override
    public Page<Editor> findAllEditors(Long idAdmin, int page, int size) {
        return adminDAO.findEditorsByAdmin(idAdmin, PageRequest.of(page, size));
    }

    @Override
    public Testimonial archiveTestimonial(Long idTestimonial, Long idAdmin) {
        Admin admin = getAdmin(idAdmin);

        return testimonialService.archiveTestimonial(idTestimonial, admin);
    }

    @Override
    public ApiKey getApiKey(Long idAdmin) {
        Admin admin = getAdmin(idAdmin);

        return admin.getApiKey();
    }
}
