import { useState } from 'react';
import type { TestimonialResponseDTO } from '../../../models/testimonial-response';

type Tag = TestimonialResponseDTO['tags'][number];

export const useTagsEditor = () => {
  const [activeTags, setActiveTags] = useState<Tag[]>([]);
  const [removedTags, setRemovedTags] = useState<Tag[]>([]);

  const initTags = (tags: Tag[]) => {
    setActiveTags(tags);
  };

  const removeTag = (tagId: number) => {
    setActiveTags((prev) => {
      const removed = prev.find((t) => t.id === tagId);
      if (removed) setRemovedTags((r) => [...r, removed]);
      return prev.filter((t) => t.id !== tagId);
    });
  };

  return { activeTags, removedTags, removeTag, initTags };
};
