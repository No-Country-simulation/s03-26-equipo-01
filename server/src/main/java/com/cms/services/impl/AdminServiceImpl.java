package com.cms.services.impl;

import com.cms.model.user.impl.admin.AdminResource;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminSQLDAO adminSQLDAO;

    public AdminServiceImpl(AdminSQLDAO adminSQLDAO) {
        this.adminSQLDAO = adminSQLDAO;
    }

    @Override
    public AdminResource getResource(Long idAdmin) {


        return null;
    }
}
