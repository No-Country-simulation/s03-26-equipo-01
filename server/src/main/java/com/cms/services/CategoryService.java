package com.cms.services;

import com.cms.model.Category;
import java.util.List;

public interface CategoryService {
    Category create(Category category);

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, Category categoryData);

    void deleteById(Long id);
}