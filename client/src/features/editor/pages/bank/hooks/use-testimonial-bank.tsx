import React, { useEffect, useState } from 'react';
import type { ReactNode } from 'react';
import { Link } from 'react-router-dom';
import useApi from '../../../../../core/api/hooks/use-api';
import testimonialBankService, {
  asocTestimonialService,
} from '../../../services/testimonial/testimonial-bank.service';
import type { TableResponseDTO } from '../components/table';
import type { TestimonialSimpleDTO } from '../components/testimonial';

interface ToastState {
  content: ReactNode;
  type: 'success' | 'error';
}

const useTestimonialBank = () => {
  const [data, setData] = useState<TableResponseDTO<TestimonialSimpleDTO>>();
  const [page, setPage] = useState(0);
  const [size] = useState(5);
  const [toast, setToast] = useState<ToastState | null>(null);

  const { get } = useApi<TableResponseDTO<TestimonialSimpleDTO>>();

  const fetchData = () => {
    get(() => testimonialBankService(page, size))
      .then(setData)
      .catch(console.error);
  };

  useEffect(() => {
    fetchData();
  }, [page]);

  const asocTestimonial = async (id: number) => {
    try {
      await asocTestimonialService(id);

      setToast({
        type: 'success',
        content: (
          <>
            <strong style={{ display: 'block', marginBottom: '4px' }}>
              ¡Listo!
            </strong>
            <span>Puedes editarlo y moderarlo desde </span>
            <Link
              to='/editor/testimonials'
              style={{ color: '#2196F3', textDecoration: 'underline' }}
            >
              Mis testimonios
            </Link>
          </>
        ),
      });

      fetchData();
    } catch {
      setToast({
        type: 'error',
        content: <span>Hubo un error al intentar asociar el testimonio.</span>,
      });
    }
  };

  const closeToast = () => setToast(null);

  return { data, page, setPage, asocTestimonial, toast, closeToast };
};

export default useTestimonialBank;
