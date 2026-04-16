import { useEffect, useState, useCallback } from 'react';
import type { TestimonialResponseDTO } from '../../../models/testimonial-response';
import type { Category } from '../../../../admin/models/category';

type Tag = TestimonialResponseDTO['tags'][number];

interface InitialState {
  category: Category | null;
  testimonialText: string;
  showVideo: boolean;
  showImage: boolean;
  activeTags: Tag[];
}

interface Changes {
  category?: { from: Category | null; to: Category | null };
  testimonialText?: { from: string; to: string };
  showVideo?: { from: boolean; to: boolean };
  showImage?: { from: boolean; to: boolean };
  tagsAdded?: Tag[];
  tagsRemoved?: Tag[];
}

export const useTrackChanges = () => {
  const [initialState, setInitialState] = useState<InitialState | null>(null);

  const setInitialValues = useCallback(
    (
      category: Category | null,
      text: string,
      video: boolean,
      image: boolean,
      tags: Tag[],
    ) => {
      setInitialState({
        category,
        testimonialText: text,
        showVideo: video,
        showImage: image,
        activeTags: tags,
      });
    },
    [],
  );

  const getChanges = useCallback(
    (
      category: Category | null,
      testimonialText: string,
      showVideo: boolean,
      showImage: boolean,
      activeTags: Tag[],
      removedTags: Tag[],
    ): Changes => {
      if (!initialState) return {};

      const changes: Changes = {};

      // Comparar categoria
      if (initialState.category?.id !== category?.id) {
        changes.category = {
          from: initialState.category || null,
          to: category || null,
        };
      }

      // Comparar texto del testimonio
      if (initialState.testimonialText !== testimonialText) {
        changes.testimonialText = {
          from: initialState.testimonialText,
          to: testimonialText,
        };
      }

      // Comparar showVideo
      if (initialState.showVideo !== showVideo) {
        changes.showVideo = {
          from: initialState.showVideo,
          to: showVideo,
        };
      }

      // Comparar showImage
      if (initialState.showImage !== showImage) {
        changes.showImage = {
          from: initialState.showImage,
          to: showImage,
        };
      }

      // Detectar tags añadidos y removidos
      const initialTagIds = new Set(initialState.activeTags.map((t) => t.id));
      const currentTagIds = new Set(activeTags.map((t) => t.id));

      const tagsAdded = activeTags.filter((t) => !initialTagIds.has(t.id));
      const tagsRemoved = initialState.activeTags.filter(
        (t) => !currentTagIds.has(t.id),
      );

      if (tagsAdded.length > 0) {
        changes.tagsAdded = tagsAdded;
      }

      if (tagsRemoved.length > 0) {
        changes.tagsRemoved = tagsRemoved;
      }

      return changes;
    },
    [initialState],
  );

  return { setInitialValues, getChanges };
};
