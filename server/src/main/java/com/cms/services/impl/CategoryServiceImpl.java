package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateResourceException;
import com.cms.model.testimonial.Category;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.CategorySQLDAO;
import com.cms.services.CategoryService;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategorySQLDAO categorySQLDAO;
    private final AdminSQLDAO adminSQLDAO;

    public CategoryServiceImpl(CategorySQLDAO categorySQLDAO, AdminSQLDAO adminSQLDAO) {
        this.categorySQLDAO = categorySQLDAO;
        this.adminSQLDAO = adminSQLDAO;
    }

    @Override
    public Category create(Category category, Long idAdmin) {
        Admin admin = adminSQLDAO.findById(idAdmin).orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));

        category.setCreator(admin);

        try {
            Category saved = categorySQLDAO.save(category);
            categorySQLDAO.flush();
            return saved;
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Ya existe una categoría con ese slug");
        }
    }

    @Override
    public List<Category> findAll() {
        return categorySQLDAO.findAllByDeletedFalse();
    }

    @Override
    public Category findById(Long id) {
        return categorySQLDAO.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), id));
    }

    @Override
    public Category update(Long id, Category categoryData) {
        Category categoryToUpdate = findById(id);

        if (categoryData.getName() != null) {
            categoryToUpdate.setName(categoryData.getName());
        }
        if (categoryData.getSlug() != null) {
            categoryToUpdate.setSlug(categoryData.getSlug());
        }
        if (categoryData.getDescription() != null) {
            categoryToUpdate.setDescription(categoryData.getDescription());
        }

        return categorySQLDAO.save(categoryToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = findById(id);
        category.setDeleted(true);
        categorySQLDAO.save(category);
    }
}