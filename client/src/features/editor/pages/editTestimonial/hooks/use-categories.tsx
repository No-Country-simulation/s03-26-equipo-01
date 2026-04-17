import { useState } from 'react';
import type { Category } from '../../../../admin/models/category';
import { getCategoryByNameService } from '../../../services/testimonial/category/get-category-by-name.service';

export const useCategories = (defaultValue?: Category | null) => {
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<Category | null>(
    defaultValue ?? null,
  );
  const [loading, setLoading] = useState(false);

  const searchByName = (name: string) => {
    if (!name) return;
    setLoading(true);
    getCategoryByNameService(name)
      .then((data) => setCategories(Array.isArray(data) ? data : [data]))
      .finally(() => setLoading(false));
  };

  return {
    categories,
    selectedCategory,
    setSelectedCategory,
    loading,
    searchByName,
  };
};
