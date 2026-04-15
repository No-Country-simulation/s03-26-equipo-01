package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import org.springframework.data.domain.Page;

public interface AdminService {
    AdminResource getResource(Long idAdmin);

    ApiKey getApiKey(Long idAdmin);

    Admin save(Admin admin);

    Editor createEditor(Editor modelEditor, Long idAdmin);

    Page<Editor> findAllEditors(Long idAdmin, int page, int size);

    Testimonial archiveTestimonial(Long idTestimonial, Long idAdmin);
}
