import { useCallback } from 'react';

interface UseMultimediaToggleReturn {
  handleVideoToggle: (value: string) => void;
  handleImageToggle: (value: string) => void;
}

export const useMultimediaToggle = (
  onVideoChange: (val: boolean) => void,
  onImageChange: (val: boolean) => void,
): UseMultimediaToggleReturn => {
  const handleVideoToggle = useCallback(
    (value: string) => {
      const isYes = value === 'si';
      onVideoChange(isYes);
      if (isYes) onImageChange(false);
    },
    [onVideoChange, onImageChange],
  );

  const handleImageToggle = useCallback(
    (value: string) => {
      const isYes = value === 'si';
      onImageChange(isYes);
      if (isYes) onVideoChange(false);
    },
    [onVideoChange, onImageChange],
  );

  return { handleVideoToggle, handleImageToggle };
};
