import { useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import type { ToastState } from '../types/toast-state';
import { updateTestimonialService } from '../services/update-testimonial.service';
import type { TestimonialUpdateDTO } from '../services/update-testimonial.types';

interface UseSaveTestimonialReturn {
  toast: ToastState | null;
  savingChanges: boolean;
  closeToast: () => void;
  handleSave: (payload: TestimonialUpdateDTO) => Promise<void>;
}

export const useSaveTestimonial = (): UseSaveTestimonialReturn => {
  const navigate = useNavigate();
  const [savingChanges, setSavingChanges] = useState(false);
  const [toast, setToast] = useState<ToastState | null>(null);

  const handleSave = useCallback(
    async (payload: TestimonialUpdateDTO) => {
      setSavingChanges(true);

      try {
        await updateTestimonialService(payload);
        setToast({
          type: 'success',
          content: (
            <>
              <strong style={{ display: 'block', marginBottom: '4px' }}>
                ¡Éxito!
              </strong>
              <span>Los cambios se han guardado correctamente.</span>
            </>
          ),
        });
        setTimeout(() => navigate('/editor/testimonials'), 1500);
      } catch {
        setToast({
          type: 'error',
          content: <span>Hubo un error al actualizar los cambios.</span>,
        });
      } finally {
        setSavingChanges(false);
      }
    },
    [navigate],
  );

  const closeToast = useCallback(() => setToast(null), []);

  return {
    toast,
    savingChanges,
    closeToast,
    handleSave,
  };
};
