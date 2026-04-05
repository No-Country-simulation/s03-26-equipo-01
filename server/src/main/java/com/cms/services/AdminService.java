package com.cms.services;

import com.cms.model.user.impl.admin.AdminResource;

public interface AdminService {
    AdminResource getResource(Long idAdmin);
}
