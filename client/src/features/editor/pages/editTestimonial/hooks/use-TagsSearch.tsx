import { useState } from 'react';
import type { Tag } from '../../../../admin/models/tag';
import { getTagsByNameService } from '../../../services/tag/get-tags-by-name.service';

export const useTagsSearch = (idTestimonial: number) => {
  const [tagOptions, setTagOptions] = useState<Tag[]>([]);
  const [loadingSearch, setLoadingSearch] = useState(false);

  const searchByName = (name: string) => {
    if (!name) {
      setTagOptions([]);
      return;
    }
    setLoadingSearch(true);
    getTagsByNameService(name, idTestimonial)
      .then(setTagOptions)
      .finally(() => setLoadingSearch(false));
  };

  return { tagOptions, loadingSearch, searchByName };
};
