import { useEffect, useState } from 'react';
import useApi from '../../../../../core/api/hooks/use-api';
import testimonialBankService, {
  asocTestimonialService,
} from '../../../services/testimonial/testimonial-bank.service';
import type { TableResponseDTO } from '../../../pages/bank/components/table';
import type { TestimonialSimpleDTO } from '../../../pages/bank/components/testimonial';

const useTestimonialBank = () => {
  const [data, setData] = useState<TableResponseDTO<TestimonialSimpleDTO>>();
  const [page, setPage] = useState(0);
  const [size] = useState(5);
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
      fetchData();
    } catch (error) {
      console.error(error);
    }
  };

  return { data, page, setPage, asocTestimonial };
};

export default useTestimonialBank;
