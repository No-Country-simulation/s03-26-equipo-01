package com.cms.services;

import com.cms.model.testimonial.Category;
import java.util.List;

public interface CategoryService {
    Category create(Category category, Long idAdmin);

    List<Category> findAll(Long idAdmin);

    Category findById(Long id);

    Category update(Long id, Category categoryData);

    void deleteById(Long id);

    List<Category> findByName(String name, Long editorId);
}